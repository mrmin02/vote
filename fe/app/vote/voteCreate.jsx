import React from 'react'
import ReactDOM from 'react-dom';
// import ColorButton from '../items/colorButton';
// import ColorButton from '../items/colorButton.jsx';
class VoteCreate extends React.Component {

  
  constructor(props){
    super(props);
    // this.changeSelect = this.changeSelect.bind(this);
  }
    changeSelect(){
      var num = document.getElementById("select_num");

      // console.log(num.options[num.selectedIndex].value);
      // console.log(num.options[num.selectedIndex].text);

      var defaultNum = 2;
      var count = num.options[num.selectedIndex].value - defaultNum;

      var div = document.getElementById("input_data");
      div.innerHTML = "";
      for(var i=0;i<count;i++){
        var tagNum = i+3;
        
        var newDiv = document.createElement("div");
        newDiv.innerHTML= tagNum +" 번";

        var newSpan = document.createElement("span");
        newSpan.innerHTML = "이미지 첨부: ";        

        var newImg = document.createElement("input");
        newImg.type = "file";
        // newImg.name = "img"+tagNum;
        newImg.name = "file";
        newImg.required = true;
        // newImg.value = "";

        var br = document.createElement("br");

        var newName = document.createElement("input");
        newName.type="text";
        // newName.name="name"+tagNum;
        newName.name="name";
        newName.required = true;
        
        // var newTextarea = document.createElement("textarea");
        // newTextarea.name="content";
        // newTextarea.cols = "30";
        // newTextarea.rows = "10";
        // newTextarea.required = "true";

        div.appendChild(newDiv);
        div.appendChild(newSpan);
        div.appendChild(newImg);
        div.appendChild(br);
        div.appendChild(newName);
        // div.appendChild(br);
        // div.appendChild(newTextarea);
        
      }
    }

    render() {
        return (
          <div>
              Vote Create
              <br/><br/><br/>
              
              <div>
                <div>title</div>
                <input type="text" name="title"/>
                <div>content</div>
                
                선택지 개수 &nbsp;&nbsp;
                <select id="select_num" defaultValue={'2'} name="count" onChange={this.changeSelect.bind(this)}>
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
                  <div id="input_data">
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