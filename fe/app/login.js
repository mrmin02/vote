import React from 'react'
import ReactDOM from 'react-dom';
class Login extends React.Component {
    render() {
        return (
            <div>
                <h3>로그인</h3>
                <div>
                    ID
                    <input type="text" id="username"name="username"/>
                </div>
                <div>
                    Password
                    <input type="password" id="password" name="password"/>
                </div>
                <button type="submit">로그인</button>
            </div>
        );
      }
}


ReactDOM.render(<Login/>,document.getElementById('login'));
