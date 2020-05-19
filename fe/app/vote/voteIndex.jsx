import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import VoteIndex from './voteIndexItem.jsx';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');


class Index extends Component{
    constructor(props){
        super(props);
        this.url = "/vote/axios";
    }
    render(){
        return(
            <div>
                진행중인 투표
                <div>
                    <a href="/vote/create">투표 생성</a>
                </div>
                <br/><br/><br/>
                <VoteIndex url={this.url}/>
            </div>
        )
    }
}


ReactDOM.render(<Index/>,document.getElementById('voteIndex'));

