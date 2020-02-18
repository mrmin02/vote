import React from 'react'
import ReactDOM from 'react-dom';

class VoteIndex extends React.Component {
    render() {
        return (
            <div>
                Vote
                <div>
                    <a href="/vote/create">투표 생성</a>
                </div>
            </div>
        );
      }
}


ReactDOM.render(<VoteIndex/>,document.getElementById('voteIndex'));
