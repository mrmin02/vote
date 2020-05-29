import React from 'react'
import ReactDOM from 'react-dom';
import ItemCard2 from '../items/ItemCard2.jsx';

import './votePreShow.css'
import './css/voteDoShow.css'
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
                    <div key={vote.name+index} className="card_div" > 
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

    render(){
        const {title} = this.state.title
        
        return(
            <div id="itemTopDiv">
                <div className="topDiv">
                    <h2>투표</h2>
                    <div className="circle">투표 마감</div>
                    {/* https://basketdeveloper.tistory.com/4 */}
                </div>
                
                <div className="list_a_tag"><a href="/vote">목록</a></div>
                <div className="div_center"><h3>{title}</h3></div>
                <div className="left_right_box">
                    <div id="item">
                        <div className="candidate">&lt;&lt; 후보 정보 &gt;&gt;</div>
                        <div className="candidate_op">**마감된 투표입니다.**</div>
                        <div className="cards">
                            <VoteShow votes={this.state.votes}/>   
                        </div>
                    </div>        
                    <div className="right_div_box">
                        실시간 투표 결과관련 내용 출력 예정
                    </div>
                </div>
                     
            </div>
        )
    }
        
}

ReactDOM.render(<Show/>,document.getElementById('voteShow'));
