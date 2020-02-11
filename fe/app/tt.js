import React from 'react'
import ReactDOM from 'react-dom';
class First extends React.Component {
    render() {
        return (
          <div>
              리엑트 첫 화면
          </div>
        );
      }
}

export default First;
ReactDOM.render(<First/>,document.getElementById('tt'));
