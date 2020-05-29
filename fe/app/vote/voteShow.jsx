import React from 'react'
import ReactDOM from 'react-dom';
import ItemCard2 from '../items/ItemCard2.jsx';
import ItemCard3 from '../items/itemCard3.jsx';
import './voteShow.css'
const axios = require('axios');

var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];
// url.substr(url.length-1,1);

const regeneratorRuntime = require("regenerator-runtime");


class VoteShow extends React.Component {
    constructor(props){
        super(props);
    }
    
    render() {
        // {this.sendSelect.bind(this,index)}
        return this.props.votes.map((vote,index)=>{
            if (vote.name != 0){
                return (
                    <div key={vote.name+index} className="card_div" onClick={this.props.event.bind(this,index)}> 
                        {/* <ItemCard key={vote.img} img={vote.img} name={vote.name} event={this.sendSelect.bind(this,index)}/>   */}
                        <ItemCard2 key={vote.img} img={vote.img} name={vote.name}/>
                    </div>
                )
            }
        })
    }
}

class Show extends React.Component{

    constructor(props){
        super(props);
        this.state = { votes: [], title: "",program:{img:"검정고무신.png",name:"검정고무신"}};
        this.aa = "aaa";
    }

    async componentDidMount(){
        let {data} = await axios.get('/vote/axios/'+param);
        // console.log(data[0]);
        this.setState({votes : data[0], title : data[1], program:data[2]});
        console.log(data);
        
    }

    sendSelect(index){
        const select  =  {"select" : index+1}
        console.log(select);
        if(!confirm("해당 후보에 투표하시겠습니까?")) return;
        

        axios.post('/vote/axios/'+param, select)
        .then((response)=>{
            if(response.data.errorMessage){
                alert(response.data.errorMessage);
                window.location.href="/vote";
            }else{
                alert(response.data.message);
                window.location.href="/vote";
            }
        });
        

    }
    render(){
        const {title} = this.state.title
        
        return(
            <div>
                <h2>투표</h2>
                <div><a href="/vote">목록으로 가기</a></div>
                <div><h3>{title}</h3></div>
                <div>프로그램 정보</div>
                <ItemCard3 img={this.state.program.img}title={this.state.program.name}/>
                <VoteShow votes={this.state.votes} event={this.sendSelect}/>                
            </div>
        )
    }
        
}

ReactDOM.render(<Show/>,document.getElementById('voteShow'));
