import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import CircleChart from '../items/circleChart.jsx';
import BarChart from '../items/barChart.jsx';
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];


// 원형 차트
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
// 그래프 차트

var barChart = {
    labels: [],// 후보 이름.
    datasets: [
        {
            title: '10대',
            backgroundColor: '#FA5858',
            values: [],
        },
        {
            title: '20대',
            backgroundColor: '#FE9A2E',
            values: [],
        },
        {
            title: '30대',
            backgroundColor: '#F7FE2E',
            values: [],
        },
        {
            title: '40대',
            backgroundColor: '#58ACFA',
            values: [],
        },
        {
            title: '50上',
            backgroundColor: '#D358F7',
            values: [],
        },
    ],
};

//
var barChart2 = {
    labels: [],// 후보 이름.
    datasets: [
        {
            title: '남성',
            backgroundColor: '#8181F7',
            values: [],
        },
        {
            title: '여성',
            backgroundColor: '#F78181',
            values: [],
        }
    ],
};


class VoteResult extends Component {
    
    constructor(props){
        super(props);
        console.log("VoteResult : constructor")
        this.state = { data:{}, circle: 0, age: 1 ,gender: 1 }       
        this.show = 0;
        this.send = {data:{},count:0}
    }
    
    async componentDidMount(){
        console.log("VoteResult : componentDidMount")
        const {data: json} =  await axios.get('/vote/result/axios/'+param);
        // json[]  0: 투표결과 , 1: 후보이름, 2: 나이별, 3: 성별별, 4:count: 투표총 횟수 ,5: 후보수, 6: 선발인원 숫자 ,7: show

        if(json[7] == 1){
            console.log("결과 공개 X")
            return this.show= 1;
        }
        this.show = json[7];            
        

        title = json[1];

        for(var i = 0; i<json[5]; i++){
            data.datasets[0].data.push(json[0][i]);
            data.labels.push(title[i]);
            barChart.labels.push(title[i]);
            barChart2.labels.push(title[i]);
        }
        var ageData = json[2] ;
        var genderData = json[3];

        for(var i =1; i<=json[5]; i++){
            for(var j = 0; j<5;j++){ // 나이별 투표 10 ~50 대
                barChart.datasets[j].values.push(ageData[i][j]);
            }
        }
        for(var i =0; i<json[5]; i++){ // 차트 더미데이터
            barChart.datasets[i].values.push(0.1);
        }

        for(var i =1; i<=json[5]; i++){
            for(var j = 0; j<2;j++){ // 성별 별로 
                barChart2.datasets[j].values.push(genderData[i][j]);
            }
        }
        
        for(var i =0; i<2; i++){ // 차트 더미데이터
            barChart2.datasets[i].values.push(0.1);

        }


        // "#" + Math.round(Math.random() * 0xffffff).toString(16)  //무작위 색상

        
        if(this.props.event){
            this.send = {data:json[0], count:json[4], win:json[6]}
            this.props.event(this.send);
        }
        
        this.send.count = json[4];
        console.log(data);

        this.setState({data})
        console.log("state:"+this.state)
        console.log("data:"+this.data)
    }

    showChart(){// 차트
        console.log("원형 차트");
        this.setState({circle: 0, age:1 ,gender:1})
        
    }

    showAge(){ // 나이별
        console.log("나이별");

        this.setState({circle: 1, age:0 ,gender:1})

    }

    showGender(){ //성별별
        console.log("성비별");

        this.setState({circle: 1, age:1 ,gender:0})
    }



    render() {
        const {data} = this.state;
        
        if(this.show == 1){
            return(
                <div>투표 결과가 공개되지 않았습니다.</div>
            )
        }
        return(
            <div>   
                { this.send.count == 0 ? (
                    <div>투표 데이터가 없습니다.</div>
                ):(
                    <div>
                        <div id="showChart">
                            {
                                this.state.circle == 0 ? (
                                    <div>
                                        <CircleChart data={this.state.data} />
                                    </div>
                                ):(
                                    this.state.age == 0 ?(
                                        <BarChart data={barChart}/>
                                    ):(
                                        <BarChart data={barChart2}/>
                                    )
                                )
                            }
                        </div>
                        <div>옵션</div>
                        <div>
                            <button  onClick={this.showChart.bind(this)}>득표수</button>
                            <button onClick={this.showAge.bind(this)}>연령별</button>
                            <button onClick={this.showGender.bind(this)}>성별</button>
                        </div>
                    </div>
                )}
            </div>
        )
      }
}



export default VoteResult



