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
        this.options = {type: 1,page : 1, size : 6, sort : "id", count: 1};
        this.url = "/vote/axios?page="+(this.options.page-1)+"&size="+this.options.size+"&sort="+this.options.sort+"&state="+this.options.type;
        
    }
    async componentDidMount(){
        // console.log(this.url);
        let {data} = await axios.get(this.url);
        // this.options.count=data.pop(); 
        this.options.count = Math.ceil((data.pop()*1.0)/this.options.size);
        console.log(this.options.count);

        this.setState({data});
    }

    clickTag(type){
        this.options.page = 1;
        this.options.type= type;
        this.setUrl();
        // this.forceUpdate();
        this.componentDidMount();
    }
    setUrl(){
        this.url = "/vote/axios?page="+(this.options.page-1)+"&size="+this.options.size+"&sort="+this.options.sort+"&state="+this.options.type;
    }
    pageClick(e, page){
        this.options.page = page;
        // console.log(this.options);
        this.setUrl();
        // this.forceUpdate();
        this.componentDidMount();
    }
    // getCount(count){
    //     console.log("자식으로 부터 온 데이터 : "+count);
    //     this.options.count = count;
    //     // <VoteIndex url={this.url} getCount={this.getCount.bind(this)}/>
    // }
    render(){
        return(
            <div>
                <h2>실시간 투표</h2>
                <div>정렬</div>
                <div className="vote_option_div">
                    <div>투표상태 : </div>
                    <div className="voteState"onClick={this.clickTag.bind(this,0)}>시작전 투표</div>
                    <div className="voteState"onClick={this.clickTag.bind(this,1)}>진행중인 투표</div>
                    <div className="voteState"onClick={this.clickTag.bind(this,2)}>마감된 투표</div>
                </div>
                <div>
                    <a href="/vote/create">투표 생성</a>
                </div>
                <br/><br/><br/>
                <VoteIndex data={this.state.data}/>
                <Pagination count={this.options.count} page={this.options.page} onChange={this.pageClick.bind(this)}/>
            </div>
        )
    }
}


ReactDOM.render(<Index/>,document.getElementById('voteIndex'));

