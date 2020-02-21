import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import CircleChart from '../items/circleChart.jsx';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];



var data= new Array();
var title=new Array(); 

class VoteResult extends Component {
    
    constructor(props){
        super(props);
        this.state = { vote:{"1":0,"2":0,"3":0,"4":0,"5":0},count:{},title:{} }       

    }

    async componentDidMount(){
        const {data: json} = await axios.get('/vote/result/axios/'+param);
        
        json[0]["result"].map(num=>{
            switch ( num ) {
                case 1:
                  this.state.vote["1"] ++;
                  break;
                case 2:
                    this.state.vote["2"] ++;
                  break;
                case 3:
                    this.state.vote["3"] ++;
                    break;
                case 4:
                    this.state.vote["4"] ++;
                    break;
                case 5:
                    this.state.vote["5"] ++;
                    break;
              }
        });


        this.state.count = json[1];// 투표 선택지 개수
        this.state.title = json[2];
        // console.log(this.state);

        for(var i = 0; i<this.state.count; i++){
            data.push(this.state.vote[i+1]);
            title.push(this.state.title[i]);
        }


    }

    render() {
        return(
            <div>
                <CircleChart data={data} title={title}/>
            </div>
        )
      }
}
class Result extends Component{
    render(){
        return(
            <div>
                실시간 투표 결과
                <br/><br/><br/>
                <VoteResult/>
            </div>
        )
    }
}


ReactDOM.render(<Result/>,document.getElementById('voteResult'));

