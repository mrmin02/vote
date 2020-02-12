import React from 'react'
import ReactDOM from 'react-dom';
class Register extends React.Component {
    render() {
        return (
            <div>
                <h3>회원가입</h3>
                <div>
                    ID
                    <input type="text" name="id"  placeholder="id" required/>
                </div>
                <div>
                    Password
                    <input type="password" name="password"  required/>
                </div>
                <div>
                    이름
                    <input type="text" name="username" placeholder="user name"  required/>
                </div>
                <div>
                    생년월일
                    <input type="date" name="birth"  required/>
                </div>
                <div>
                    전화번호
                    <input type="tel" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="010-1234-1234" required/>
                </div>
                <div>
                    e-Mail
                    <input type="email" name="email" placeholder="example@example.com"  required/>
                </div>
                <button type="submit">회원가입</button>
            </div>
        );
      }
}


ReactDOM.render(<Register/>,document.getElementById('register'));
