import React from 'react'
import ReactDOM from 'react-dom';
class Hello extends React.Component {
    render() {
        return (
          <div>
              리엑트 Home
          </div>
        );
      }
}

// export default Hello;
ReactDOM.render(<Hello/>,document.getElementById('test'));
