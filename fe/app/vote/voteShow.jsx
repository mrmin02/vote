import React from 'react'
import ReactDOM from 'react-dom';
import ItemCard from '../items/itemCard.jsx';
import ItemCard2 from '../items/ItemCard2.jsx';
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
        this.state = { votes: [] };
    }

    async componentDidMount(){
        let {data : votes} = await axios.get('/vote/axios/'+param);
        
        this.setState({votes});
        // console.log(this.state);
        
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


    render() {
        const { votes } = this.state
        console.log(votes);
        return votes.map((vote,index)=>{
            if (vote.name != 0){
                return (
                    <div key={vote.name+index} className="card_div" onClick={this.sendSelect.bind(this,index)}> 
                        {/* <ItemCard key={vote.img} img={vote.img} name={vote.name} event={this.sendSelect.bind(this,index)}/>   */}
                        <ItemCard2 key={vote.img} img={vote.img} name={vote.name}/>
                    </div>
                )
            }
        })
    }
}

function Show(){
        return(
            <div>
                <div><a href="/vote">목록으로 가기</a></div>
                <VoteShow/>                
            </div>
        )
}

ReactDOM.render(<Show/>,document.getElementById('voteShow'));
