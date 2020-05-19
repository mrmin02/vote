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
        let {data : title} = await axios.get(this.props.url);
        // '/vote/axios'

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
export default VoteIndex;

