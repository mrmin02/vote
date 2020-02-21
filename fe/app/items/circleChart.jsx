import React ,{Component} from 'react';
import { Chart } from '@bit/primefaces.primereact.chart';

const data = {
  labels: ['A', 'B', 'C','aa'],
  datasets: [
    {
      data: [300, 50, 300],
      backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56','#FFCE56','#FFCE56'],
      hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56','#FFCE56','#FFCE56']
    }
  ]
};

class CircleChart extends Component{
    constructor(props){
        super(props)
        // var a= [] ;
        // a = this.props.title
        // console.log();
        this.props.title.push("asd")
        console.log(   this.props.title  );  //  []  0:--   
        // console.log(;
      
        console.log(data.labels); //   ["aa","aa","aaa"]     ->  0: aa 1: bbb
        // data.labels = this.props.title;
        // console.log(data.labels);
    }
    render(){
        return(
            <div style={{ width: 400 }}>
                <Chart type='pie' data={data}/>
            </div>
        )
    }
}
 
export default CircleChart
