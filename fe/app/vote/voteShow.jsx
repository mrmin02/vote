import React from 'react'
import ReactDOM from 'react-dom';

class VoteShow extends React.Component {
    constructor(props){
        super(props);
        console.log(props);
        // console.log(document.getElementById("voteShow").tt);
    }
    render() {
        return (
            <div>
                Vote Show React
                <div>
                    <a href="/vote">투표 목록</a>
                </div>
            </div>
        );
      }
}


ReactDOM.render(<VoteShow/>,document.getElementById('voteShow'));
