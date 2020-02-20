import React from 'react'
import ReactDOM from 'react-dom';
import ItemCard from '../items/itemCard.jsx';
const axios = require('axios');

var url = document.location.href;
var param = url.substr(url.length-1,1);

// function MakeCard(data){
//     return(
//         <ul>
//             {data.map(vote=>
//                 <ItemCard img={vote.img} name={vote.name}/>
//             )}

//         </ul>
//     );
// }



class VoteShow extends React.Component {
    constructor(props){
        super(props);
        this.state = { votes: [] };
    }

    async componentDidMount(){
        let {data : votes} = await axios.get('/vote/axios/'+param);
        this.setState({votes});
    }

    render() {
        const { votes } = this.state

        return votes.map(vote=>{
            return (
                <div> 
                    {/* <MakeCard data={vote} /> */}
                    <ItemCard img={vote.img} name={vote.name}/>
                </div>
            )
        })
    }
}


ReactDOM.render(<VoteShow/>,document.getElementById('voteShow'));
