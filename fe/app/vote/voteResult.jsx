import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import CircleChart from '../items/circleChart.jsx';

const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];


var dataStore = { 
    vote:{"1":0,"2":0,"3":0,"4":0,"5":0},
    title:[], data:[]
  }
var title = [];

var data = {
    labels: [],
    datasets: [
      {
        data: [],
        backgroundColor: ['#FA5858', '#FE9A2E', '#FFFF00','#80FF00','#00FFFF','#0080FF','#BF00FF','#848484'],
        hoverBackgroundColor: ['#FA5858', '#FE9A2E', '#FFFF00','#80FF00','#00FFFF','#0080FF','#BF00FF','#848484']
      }
    ]
  };


class VoteResult extends Component {
    
    constructor(props){
        super(props);
        console.log("VoteResult : constructor")
        this.state = { data:{} }       
    }
    
    async componentDidMount(){
        console.log("VoteResult : componentDidMount")
        const {data: json} =  await axios.get('/vote/result/axios/'+param);

        console.log("--------------------"+json[0]["result"]);
        console.log("---------------------2"+json[2]);
        // json[0]["result"].map(num=>{
        //     switch ( num ) {
        //         case 1:
        //           dataStore.vote["1"] ++;
        //           break;
        //         case 2:
        //             dataStore.vote["2"] ++;
        //           break;
        //         case 3:
        //             dataStore.vote["3"] ++;
        //             break;
        //         case 4:
        //             dataStore.vote["4"] ++;
        //             break;
        //         case 5:
        //             dataStore.vote["5"] ++;
        //             break;
        //       }
        // });

        // const count = json[1];// 투표 선택지 개수

        title = json[2];

        for(var i = 0; i<json[1]; i++){
            // data.datasets[0].data.push(dataStore.vote[i+1]);
            data.datasets[0].data.push(json[0]["result"][i]);
            data.labels.push(title[i]);
        }

        // this.state.title = dataStore.title
        // this.state.data = dataStore.data

        this.setState({data})
    }

    render() {
        console.log("VoteResult : render")
        console.log("this.state: "+this.state.data)
        const {data} = this.state;
        console.log(data);
        return(
            <div>
                <CircleChart data={data} />
            </div>
        )
      }
}
class Result extends Component{
    render(){
        return(
            <div>
                <a href="/vote">목록</a>
                실시간 투표 결과
                <br/><br/><br/>
                <VoteResult/>
            </div>
        )
    }
}


// ReactDOM.render(<Result/>,document.getElementById('voteResult'));
export default VoteResult



