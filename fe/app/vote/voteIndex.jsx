import React, {Component}from 'react'
import ReactDOM from 'react-dom';
import VoteIndex from './voteIndexItem.jsx';
import './voteIndex.css';
import { Pagination } from '@material-ui/lab';
const regeneratorRuntime = require("regenerator-runtime");
const axios = require('axios');


class Index extends Component{
    constructor(props){
        super(props);
        this.state = { data: [] };
        this.options = {type: 1,page : 1, size : 6, sort : "id", count: 1, program: 0, text: ""};
        this.url = "/vote/axios?page="+(this.options.page-1)+"&size="+this.options.size+"&sort="+this.options.sort+"&state="+this.options.type+"&program="+this.options.program+"&text="+this.options.text;
        
    }
    async componentDidMount(){
        this.getVoteItemWithOptionPaging();

        let {data} = await axios.get('/vote/program/axios')
        var parentsDiv = document.getElementById("program_option")

        const programOptionTitle = document.createElement("div");
        programOptionTitle.innerHTML = "프로그램 ▶ "
        parentsDiv.appendChild(programOptionTitle);
        data.map((program,index)=>{
            var div = document.createElement("div");

            div.innerHTML = program.name;
            div.className = "voteState program"
            div.title= program.id

            div.onclick = this.clickProgramName.bind(this,program.id);

            parentsDiv.appendChild(div);
        })
    }
    async getVoteItemWithOptionPaging(){
        // console.log(this.url)
        let {data} = await axios.get(this.url);
        // this.options.count=data.pop(); 
        this.options.count = Math.ceil((data.pop()*1.0)/this.options.size);
        // console.log(this.options.count);

        this.setState({data});
    }

    clickTag(type){
        this.options.page = 1;
        this.options.type= type;
        this.setUrl();
        // this.forceUpdate();
        // this.componentDidMount();
        this.getVoteItemWithOptionPaging();
    }
    clickProgramName(id){
        // console.log("프로그램 클릭  id : "+id);
        this.options.page = 1;
        this.options.program = id;
        this.setUrl();
        this.getVoteItemWithOptionPaging();
    }
    setUrl(){
        this.url = "/vote/axios?page="+(this.options.page-1)+"&size="+this.options.size+"&sort="+this.options.sort+"&state="+this.options.type+"&program="+this.options.program+"&text="+this.options.text;
    }
    pageClick(e, page){
        this.options.page = page;
        // console.log(this.options);
        this.setUrl();
        // this.forceUpdate();
        // this.componentDidMount();
        this.getVoteItemWithOptionPaging();
    }
    // getCount(count){
    //     console.log("자식으로 부터 온 데이터 : "+count);
    //     this.options.count = count;
    //     // <VoteIndex url={this.url} getCount={this.getCount.bind(this)}/>
    // }

    onSearchEvnet(){
        console.log("버튼 클릭");
        const text = document.getElementById("searchInput").value
        // if(!text){
        //     return alert("검색어를 입력하세요")
        // }
        
        this.options.text = text;
        
        this.setUrl();
        this.getVoteItemWithOptionPaging();

    }

    render(){
        const clickTypes = document.getElementsByClassName("type")
        const clickProgram = document.getElementsByClassName("program")
        for( var i=0; i<clickTypes.length; i++){
            if(clickTypes[i].title == this.options.type)
                clickTypes[i].style.color="rgba(221, 44, 192, 1)"
            else
                clickTypes[i].style.color="black"
        }
        for( var i=0; i<clickProgram.length; i++){
            if(clickProgram[i].title == this.options.program)
                clickProgram[i].style.color="rgba(221, 44, 192, 1)"
            else
                clickProgram[i].style.color="black"
        }


        return(
            <div>
                <h2>실시간 투표</h2>
                <div className="vote_options_select_div">
                    <div className="options">
                        <div>정렬</div>
                        <div className="vote_option_div">
                            <div>투표상태 ▶ </div>
                            <div className="voteState type" title="0" onClick={this.clickTag.bind(this,0)}>시작전 투표</div>
                            <div className="voteState type" title="1" onClick={this.clickTag.bind(this,1)}>진행중인 투표</div>
                            <div className="voteState type" title="2" onClick={this.clickTag.bind(this,2)}>마감된 투표</div>
                        </div>
                        <div id="program_option"className="vote_option_div">
                            {/* componentDidMount 에서 추가 */}
                        </div>
                        <div>
                            <a href="/vote/create">투표 생성</a>
                        </div>
                    </div>
                    <div className="search_vote">
                        검색
                        <input type="text" id="searchInput"/>
                        <button type="click" onClick={this.onSearchEvnet.bind(this)}>검색</button>
                    </div>
                </div>
                <br/><br/><br/>
                <div className="voteItem">
                    <VoteIndex data={this.state.data}/>
                </div>
                <div className="pagingCenter">
                    <Pagination count={this.options.count} page={this.options.page} onChange={this.pageClick.bind(this)}/>
                </div>
            
            </div>
        )
    }
}


ReactDOM.render(<Index/>,document.getElementById('voteIndex'));


