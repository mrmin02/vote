import React, {Component}from 'react';
import ReactDOM from 'react-dom';
import VoteIndex from './voteIndexItem.jsx';

class PreIndex extends Component{
    constructor(props){
        super(props);
        this.url = "/vote/pre/axios";
    }
    render(){
        return(
            <div>
                시작전 투표
                <div>
                    <a href="/vote/create">투표 생성</a>
                </div>
                <br/><br/><br/>
                <VoteIndex url={this.url}/>
            </div>
        )
    }
}


ReactDOM.render(<PreIndex/>,document.getElementById('voteIndex'));