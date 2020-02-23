import React, {Component}from 'react'
import ReactDOM from 'react-dom';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

class VoteIndex extends Component {
    constructor(props){
        super(props);
        this.state = { title: [] };
    }

    async componentDidMount(){
        let {data : title} = await axios.get('/vote/axios');

        this.setState({title});
        
    }
    render() {
        const { title } = this.state;
        return title.map((tt,index)=>{
            return (
                <div key={'div'+index}>
                    <a href={"/vote/"+tt.id} key={index}>{tt.title}</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href={'/vote/result/'+tt.id}>실시간 투표 결과</a>
                </div>
            )
        })
      }
}
class Index extends Component{
    render(){
        return(
            <div>
                Vote
                <div>
                    <a href="/vote/create">투표 생성</a>
                </div>
                <br/><br/><br/>
                <VoteIndex/>
            </div>
        )
    }
}


ReactDOM.render(<Index/>,document.getElementById('voteIndex'));

