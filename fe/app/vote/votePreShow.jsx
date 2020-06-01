import React from 'react'
import ReactDOM from 'react-dom';
import ItemCard2 from '../items/itemCard2.jsx';
import ItemCard3 from '../items/itemCard3_big.jsx';
// import './voteShow.css'
import './votePreShow.css'
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
                    <div key={vote.name+index} className="card_div"> 
                        <ItemCard2 key={vote.img} img={vote.img} name={vote.name} info={vote.info}/>
                    </div>
                )
            }
        })
    }
}

class Show extends React.Component{

    constructor(props){
        super(props);
        this.state = { votes: [], title: "",program:{img:"검정고무신.png",name:"검정고무신",info:"설명"}, date:{startTime:"000",endTime:"0000",resultShowTime:"0000"}};
        this.stTime;
        this.edTime;
        this.rsTime; // 투표 집계공개 시간
    }

    async componentDidMount(){
        let {data} = await axios.get('/vote/axios/'+param);
        console.log(data);
        this.setState({votes : data[0], title : data[1], program:data[2], date: data[3]});
        console.log(data);

        
    }
    setDate(){
        
        var start = String(this.state.date.startTime);
        var end = String(this.state.date.endTime);
        var resultShow = String(this.state.date.resultShowTime);
        
        // console.log(typeof(start));
        this.stTime = start.substr(0,4)+"-"+start.substr(4,2)+"-"+start.substr(6,2)+" "+start.substr(8,2)+":"+start.substr(10,2);
        this.edTime = end.substr(0,4)+"-"+end.substr(4,2)+"-"+end.substr(6,2)+" "+end. substr(8,2)+":"+end.substr(10,2);
        this.rsTime = resultShow.substr(0,4)+"-"+resultShow.substr(4,2)+"-"+resultShow.substr(6,2)+" "+resultShow. substr(8,2)+":"+resultShow.substr(10,2);

        
        
    }
    render(){
        const {title} = this.state.title
        this.setDate();
        console.log("render")
        return(
            <div id="itemTopDiv">
                <div className="topDiv">
                    <h2>투표</h2>
                    <div className="circle">투표 시작전</div>
                </div>
                
                <div className="list_a_tag"><a href="/vote">목록</a></div>
                <div id="item">
                    <div className="div_center"><h3>{title}</h3></div>
                    <div id="program_info">
                        <div className="img">
                        <ItemCard3 img={this.state.program.img}title={this.state.program.name}/>
                        </div>                    
                        <div className="info">
                            <div className="div_center textColor">★☆프로그램 소개☆★</div>
                            <div>{this.state.program.info}</div>
                        </div>
                    </div>
                    <div className="text_center br_div">투표기간</div>
                    <div className="text_center">시작: {this.stTime}</div>
                    <div className="text_center">마감: {this.edTime}</div>
                    <div className="text_center">집계공개: {this.rsTime}</div>
                    <div className="candidate">&lt;&lt; 후보 정보 &gt;&gt;</div>
                    <div className="candidate_op">★☆후보 클릭 시 관련 정보로 이동☆★</div>
                    <div className="cards">
                        <VoteShow votes={this.state.votes} />   
                    </div>
                    
                </div>             
            </div>
        )
    }
        
}

ReactDOM.render(<Show/>,document.getElementById('voteShow'));
