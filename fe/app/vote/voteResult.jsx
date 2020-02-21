import React, {Component}from 'react'
import ReactDOM from 'react-dom';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

var url = document.location.href;
const num = url.split('/');
var param = num[num.length-1];


class VoteResult extends Component {
    constructor(props){
        super(props);
        this.state = { title: [] };
    }

    async componentDidMount(){
        const aa = await axios.get('/vote/result/axios/'+param);
        console.log(aa);

        // this.setState({title});
        
        // console.log(this.state.title);
    }

    render() {
        // const { title } = this.state;
        // return title.map((tt,index)=>{
        //     return (
        //         <div key={'div'+index}>
        //             <a href={"/vote/"+tt.id} key={index}>{tt.title}</a>
        //             <a href={'/vote/'+tt.id+'/result'}>실시간 투표 결과</a>
        //         </div>
        //     )
        // })
        return(
            <div>결과 결과 결과</div>
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

