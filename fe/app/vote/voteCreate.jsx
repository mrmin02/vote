import React from 'react'
import ReactDOM from 'react-dom';
import './css/createCss.css'
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');
import jQuery from "jquery";
window.$ = window.jQuery = jQuery;

class VoteCreate extends React.Component {

  
  constructor(props){
    super(props);
    // this.changeSelect = this.changeSelect.bind(this);
    this.candidateNum = 0;

    this.childTag = '<div class="grid">'+
    '<table class="candidateTable">'+
      '<tbody>'+
        '<tr>'+
          '<td rowSpan="2">'+
            '<div class="imgDiv">'+
              '<img class="candidateImg"src="/uploads/검정고무신.png" alt=""/>'+
            '</div>'+
          '</td>'+
          '<td>'+
            '<input type="text" name="name" placeholder="이름" required/>'+
            '<input type="file" class="fileImg" name="file" required/>'+
          '</td>'+
        '</tr>'+
        '<tr>'+
          '<td>'+
            '<textarea type="text" class="candidateInfo"placeholder="내용을 입력하세요"  name="info"></textarea>'+
          '</td>'+
        '</tr>'+
        '<tr>'+
          '<td></td>'+
          '<td><div class="tag">태그</div><input class="tagInput"type="text" placeholder="#태그"/></td>'+
        '</tr>'+
    '</tbody>'+
  '</table>'+
'</div>';
    
  }
    changeCandidateNum(num){
        console.log(num)

        var tag = this.childTag
        
        $("#candidateInfoItem").empty();
        for(var i = 1 ; i<num; i ++){
          tag += this.childTag
        }
        
        $("#candidateInfoItem").append($(tag))
            $('.fileImg').bind('change',function (e){
            console.log("e:"+e.target.files[0].name);
            var reader = new FileReader();
            reader.readAsDataURL( e.target.files[0] );
            reader.onloadend = function (){
              e.target.parentElement.previousSibling.firstChild.firstChild.src = reader.result
            }
            
            // .closest("tr").find('img[class="candidateImg"]').src = e.target.files[0]
        })
        
    }
    candidateGener(e){
      e.preventDefault();
      var num = $('#candidateCount').val()
      if(num >100)
        return alert("후보자 수는 100 을 초과할 수 없습니다.")
      else if(num <0)
        return alert("후보자 수는 0보다 작을 수 없습니다.")
      else if(num == 0 || num == "")
        return $("#candidateInfoItem").empty();
      this.changeCandidateNum(num)
      
    }


    async componentDidMount(){    
      let {data} = await axios.get('/vote/program/axios');
      console.log(data);
      
      var select  = document.getElementById("program_select");
      
      data.map((program,index)=>{
        var option = document.createElement("option");
        option.value = program.id;
        option.text = program.name;
        if(index == 0)
          option.defaultSelected = true;
        select.appendChild(option);
      })
            
  }
  fileImg(e){
    console.log("aaaa");
  }
  cancle(e){
    e.preventDefault()
    if(confirm("투표 개설을 취소하시겠습니까?"))
      return window.location = '/vote'
    else return
  }
  checkForm(e){
    if($('#candidateCount').val()<2){
      e.preventDefault();
      return alert("후보자 수는 2명 이상이어야 합니다.")
    }else if($('#selectedNum').val()<=0){
      e.preventDefault();
      return alert("선발인원은 1명 이상이어야 합니다.")
    }else if($('#selectedNum').val()>=$('#candidateCount').val()){
      e.preventDefault();
      return alert("선발인원은 후보자 수 보다 적어야 합니다.")
    }else if($("#voteCanNum").val()>0){
      e.preventDefault();
      return alert("다중투표 횟수는 0보다 커야합니다.")
    }
    if(confirm("해당 설정으로 투표를 개설하시겠습니까?")){
      return;
    }
    return e.preventDefault();
  }
    render() {
        return (
          <div id="voteCreate">
              <table className="table">
                  <tbody>
                    <tr>
                      <td>프로그램</td>
                      <td>
                        <select id="program_select"  name="program_id" required>                
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <td>썸네일 이미지</td>
                      <td><input type="file" name="thumbnail" required/><br/>  </td>
                    </tr>
                    <tr>
                      <td>투표제목</td>
                      <td><input type="text" name="title" required/></td>
                    </tr>
                    <tr>
                      <td>후보자 수</td>
                      <td><input type="number" className="inputTextRight inputWidth" id="candidateCount"name="count" required/>명 &nbsp;<button onClick={this.candidateGener.bind(this)}>체크</button></td>
                    </tr>
                    <tr>
                      <td>선발인원</td>
                      <td><input type="number" id="selectedNum" name="selectNum" className="inputTextRight inputWidth" required/>명</td>
                    </tr>
                    <tr>
                      <td>다중투표</td>
                      <td>1인 최대<input type="number" id="voteCanNum" name="voteCanNum" className="inputTextRight inputWidth" required/>표</td>
                    </tr>
                    <tr>
                      <td>투표기간</td>
                      <td><input type="datetime-local" id="startTime" name="startTime" required/> - <input type="datetime-local" id="endTime" name="endTime" required/></td>
                    </tr>
                    <tr>
                      <td>결과공개시간</td>
                      <td><input type="datetime-local" name="resultShowTime" required/></td>
                    </tr>
                    <tr>
                      <td>진행사항 공개여부</td>
                      <td><input type="radio" name="show" value="0"/> 공개 <input type="radio" name="show" value="1"/> 비공개</td>
                    </tr>
                  </tbody>
              </table>

              <div className="candidateList">
                <div className="candidateItem">
                  <div className="candidate">후보자</div>
                  <div id="candidateInfoItem">
                </div>
              </div>
            </div>

            {/* <div className="roundGrid">
              <div>회차소개</div>
              <div className="roundInfo">
                <div>< input type="file" name="zzz" required/></div><br/>
                <div><textarea className="roundTextArea"type="text" placeholder="내용을 입력하세요"  required></textarea></div>
              </div>
            </div> */}

            <div className="Notification">※ 투표 개설 시 수정이 불가능하며 투표 시작 전 까지 삭제만 가능합니다.</div>
            <div className="formButton"><button type="submit" onSubmit={this.checkForm.bind(this)}>개설</button>&nbsp;&nbsp;<button type="click" onClick={this.cancle.bind(this)}>취소</button></div>
          </div>
        );
      }
}



ReactDOM.render(<VoteCreate/>,document.getElementById('voteCreate'));