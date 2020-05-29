import React from 'react'
import ReactDOM from 'react-dom';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');

class VoteCreate extends React.Component {

  
  constructor(props){
    super(props);
    // this.changeSelect = this.changeSelect.bind(this);
    
  }
    changeSelect(num){
      // --- 셀렉트 박스
      var num = document.getElementById("select_num");

      var defaultNum = 2;
      var count = num.options[num.selectedIndex].value - defaultNum;

      var div = document.getElementById("input_data_event");
      div.innerHTML = "";
      for(var i=0;i<count;i++){
        var tagNum = i+3;
        
        var newDiv = document.createElement("div");
        newDiv.innerHTML= tagNum +" 번";

        var newSpan = document.createElement("span");
        newSpan.innerHTML = "이미지 첨부: ";        

        var newImg = document.createElement("input");
        newImg.type = "file";
        
        newImg.name = "file";
        newImg.required = true;
        

        var br = document.createElement("br");

        var newName = document.createElement("input");
        newName.type="text";
        
        newName.name="name";
        newName.required = true;
        
        

        div.appendChild(newDiv);
        div.appendChild(newSpan);
        div.appendChild(newImg);
        div.appendChild(br);
        div.appendChild(newName);

        
      }
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

    render() {
        return (
          <div>
              Vote Create
              <br/><br/><br/>
              
              <div id="vote_Create" className="vote_Create">
                <div>제목</div>
                <input type="text" name="title" required/>

                <div>시작시간</div>
                <input type="datetime-local" name="startTime" required/>
                <div>종료시간</div>
                <input type="datetime-local" name="endTime" required/>

                <div>content</div>
                
                선택지 개수 &nbsp;&nbsp;                
                <select id="select_num"  defaultValue={'2'} name="count" onChange={this.changeSelect.bind(this)}
                required>
                  <option value="2" defaultValue>2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                  <option value="6">6</option>
                  <option value="7">7</option>
                  <option value="8">8</option>
                  <option value="9">9</option>
                  <option value="10">10</option>
                </select>
                <div>대표 이미지</div>
                <input type="file" name="thumbnail" required/><br/>  
                <br/><br/>

                <div>프로그램</div>
                <select id="program_select"  name="program_id" required>                
                </select>
                <br/><br/>

                <div>1 번</div>
                  이미지 첨부: <input type="file" name="file" required/><br/>  
                  <input type="text" name="name" required/><br/>
                  {/* <textarea name="content" id="" cols="30" rows="10" required></textarea> */}
                  <div>2 번</div>
                  이미지 첨부: <input type="file" name="file" required/><br/>  
                  <input type="text" name="name" required/><br/>
                  {/* <textarea name="content" id="" cols="30" rows="10" required></textarea> */}
                  <div id="input_data_event">
                  </div>
                </div>

              <br/><br/><br/>
              <div>
                <a href="/vote">목록</a>
              </div>
              <input type="submit" value="투표 생성"/>
              {/* <ColorButton/> */}
          </div>
        );
      }
}

// function changeSelect

ReactDOM.render(<VoteCreate/>,document.getElementById('voteCreate'));