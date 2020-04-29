import React from 'react'
import ReactDOM from 'react-dom';
// import ColorButton from '../items/colorButton';
// import ColorButton from '../items/colorButton.jsx';
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
      //-------------------------- 라디오 버튼
      // var defaultNum = 2;
      // var count = num - defaultNum;

      // var div = document.getElementById("input_data_event");
      // div.innerHTML = "";
      // for(var i=0;i<count;i++){
      //   var tagNum = i+3;
        
      //   var newDiv = document.createElement("div");
      //   newDiv.innerHTML= tagNum +" 번";

      //   var newSpan = document.createElement("span");
      //   newSpan.innerHTML = "이미지 첨부: ";        

      //   var newImg = document.createElement("input");
      //   newImg.type = "file";
        
      //   newImg.name = "file";
      //   newImg.required = true;
        

      //   var br = document.createElement("br");

      //   var newName = document.createElement("input");
      //   newName.type="text";
        
      //   newName.name="name";
      //   newName.required = true;
        
        

      //   div.appendChild(newDiv);
      //   div.appendChild(newSpan);
      //   div.appendChild(newImg);
      //   div.appendChild(br);
      //   div.appendChild(newName);

        
      // }
    }

    render() {
        return (
          <div>
              Vote Create
              <br/><br/><br/>
              
              <div id="vote_Create" className="vote_Create">
                <div>title</div>
                <input type="text" name="title"/>
                <div>content</div>
                
                선택지 개수 &nbsp;&nbsp;
                
                {/* <label><input defaultChecked type="radio" name="count" value="2" onChange={this.changeSelect.bind(this,2)}/>2</label>&nbsp;
                <label><input type="radio" name="count" value="3" onChange={this.changeSelect.bind(this,3)}/>3</label>&nbsp;
                <label><input type="radio" name="count" value="4" onChange={this.changeSelect.bind(this,4)}/>4</label>&nbsp;
                <label><input type="radio" name="count" value="5" onChange={this.changeSelect.bind(this,5)}/>5</label>&nbsp; */}
                
                <select id="select_num"  defaultValue={'2'} name="count" onChange={this.changeSelect.bind(this)}
                className="">
                  <option value="2" defaultValue>2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                </select>

                
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