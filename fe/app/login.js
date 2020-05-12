import React from 'react'
import ReactDOM from 'react-dom';
import './auth/login.css';
class Login extends React.Component {
    render() {
        return (
            <div>
                <div>
                    <input className="login_input" type="text" id="username"name="username" placeholder="이메일"/>
                </div>
                <div>
                    <input className="login_input" type="password" id="password" name="password" placeholder="비밀번호" />
                </div>
                <button type="submit" className="login_button">로그인</button>
            </div>
        );
      }
}


ReactDOM.render(<Login/>,document.getElementById('login'));
