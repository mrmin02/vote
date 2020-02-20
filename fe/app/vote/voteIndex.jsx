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
        console.log(title);

        this.setState({title});
        
        // this.setState({title});
        // console.log(this.state.user);
        console.log(this.state.title);
    }
    render() {
        const { title } = this.state;
        return title.map((tt,index)=>{
            return (
                <div>
                    <a href={"/vote/"+tt.id} key={index}>{tt.title}</a>
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

