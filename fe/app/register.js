import React from 'react';
import ReactDOM from 'react-dom';
import "./auth/register.css";
const axios = require('axios');
const regeneratorRuntime = require("regenerator-runtime");

class Register extends React.Component {
    
    constructor(props){
        super(props);
        this.state = { check : 0};
        document.getElementById("register_form").addEventListener("submit",this.result_submit.bind(this));
    }

    result_submit(e){
        
        const pwd = document.getElementById("pwd").value;
        const pwd_check = document.getElementById("pwd_check").value;
        
        if(this.state.check ==0){
            e.preventDefault();    
            alert("아이디 중복체크를 해주세요");
        }else if(pwd != pwd_check){
            e.preventDefault();    
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
        
        
    }
    checkId(e){
        e.preventDefault();
        console.log("아이디 중복확인");
        console.log(this.state.check);
        var id = document.getElementById("id").value;

        if(!id)  return alert("이메일을 입력해 주세요");

        axios.get("/auth/register/checkId/"+id)
        .then((response)=>{
            let {data} = response;

            alert(data.message);

            this.setState({check:data.check});
            console.log(this.state.check);
                
        });
    }
        
    

    render() {
        
        return (
            <div>
                <table className="register_table">
                    <tbody>
                        <tr>
                            <td><input className="register_input" type="text" id="id" name="id"  placeholder="이메일" required/></td>
                            <td><button className="check_button"  type="click" onClick={this.checkId.bind(this)}> 중복확인 </button></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="password" name="password" id="pwd" placeholder="비밀번호" required/></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="password" name="password_check" id="pwd_check" placeholder="비밀번호 확인" required/></td>
                        </tr>
                        <tr>
                            <td colSpan="2"><hr></hr></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="text" name="username" placeholder="이름"  required/></td>
                        </tr>
                        <tr>
                            <td><input className="register_birth" type="date" name="birth"  required/></td>
                        </tr>
                        <tr>
                            <td className="register_left_td"><input className="register_gender" type="radio" id="male" name="gender" value="male" required/>남자</td>
                            <td className="register_right_td"><input type="radio" id="female" name="gender" value="female" required/>여자</td>
                        </tr>
                        <tr>
                            <td colSpan="2"><hr></hr></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="tel" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="010-1234-1234" required/></td>
                        </tr>
                        <tr>
                            <td ><input className="register_input" type="text" name="addr" placeholder="도로명 주소" required/></td>
                            {/* <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="시 도"/></td>
                            <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="시 군 구"/></td>
                            <td colSpan="0.3"><input className="addr_input" type="text" name="addr" placeholder="동 명 읍"/></td> */}
                        </tr>
                        <tr>
                            <td><input className="register_input" type="text" placeholder="기타주소" required/></td>
                        </tr>
                        <tr>
                            <td><input className="register_input" type="email" name="email" placeholder="example@example.com"  required/></td>
                        </tr>
                        
                    </tbody>
                </table>
                <button className="submit_button" type="submit">회원가입</button>
            </div>

        );
      }
}


ReactDOM.render(<Register/>,document.getElementById('register'));
