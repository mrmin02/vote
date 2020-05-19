import React, {Component}from 'react';
import ReactDOM from 'react-dom';
import VoteIndex from './voteIndexItem.jsx';

class EndIndex extends Component{
    constructor(props){
        super(props);
        this.url = "/vote/end/axios";
    }
    render(){
        return(
            <div>
                마감된 투표
                <div>
                    <a href="/vote/create">투표 생성</a>
                </div>
                <br/><br/><br/>
                <VoteIndex url={this.url}/>
            </div>
        )
    }
}


ReactDOM.render(<EndIndex/>,document.getElementById('voteIndex'));