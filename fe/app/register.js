import React from 'react';
import ReactDOM from 'react-dom';
import "./auth/register.css";
class Register extends React.Component {
    render() {
        return (
            <div>
                <table className="register_table">
                    <tbody>
                        <tr>
                            <td><input className="login_input" type="text" name="id"  placeholder="이메일" required/></td>
                            <td><button type="click"> 중복확인 </button></td>
                        </tr>
                        <tr>
                            <td><input className="login_input" type="password" name="password"  placeholder="비밀번호" required/></td>
                        </tr>
                        <tr>
                            <td><input className="login_input" type="password" name="password_check"  placeholder="비밀번호 확인" required/></td>
                        </tr>
                        <tr>
                            <td colSpan="2"><hr></hr></td>
                        </tr>
                        <tr>
                            <td><input className="login_input" type="text" name="username" placeholder="이름"  required/></td>
                        </tr>
                        <tr>
                            <td><input className="login_input" type="date" name="birth"  required/></td>
                        </tr>
                        <tr>
                            <td><input type="radio" id="male" name="gender" value="male"/>남자 <input type="radio" id="female" name="gender" value="female"/>여자</td>
                        </tr>
                        <tr>
                            <td colSpan="2"><hr></hr></td>
                        </tr>
                        <tr>
                            <td><input className="login_input" type="tel" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="010-1234-1234" required/></td>
                        </tr>
                        <tr>
                            <td ><input className="login_input" type="text" name="addr" placeholder="도로명 주소"/></td>
                            {/* <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="시 도"/></td>
                            <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="시 군 구"/></td>
                            <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="동 명 읍"/></td> */}
                        </tr>
                        <tr>
                            <td><input className="login_input" type="text" placeholder="기타주소"/></td>
                        </tr>
                        <tr>
                            <td><input className="login_input" type="email" name="email" placeholder="example@example.com"  required/></td>
                        </tr>
                        <tr>
                            <td><button type="submit">회원가입</button></td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>

        );
      }
}


ReactDOM.render(<Register/>,document.getElementById('register'));
