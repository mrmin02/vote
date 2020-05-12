import React ,{Component} from 'react';
import { Chart } from '@bit/primefaces.primereact.chart';

var data = {
  labels: ['A', 'B', 'C','aa'],
  datasets: [
    {
      data: [300, 50, 300],
      backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56','#FFCE56','#848484'],
      hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56','#FFCE56','#FFCE56']
    }
  ]
};
class CircleChart extends Component{
    constructor(props){
        super(props)
        console.log("CircleChart : constructor")
    }

    render(){
      console.log("CircleChart : render")
        return(
            <div style={{ width: 400 }}>
                <Chart type='pie' data={this.props.data}/>
            </div>
        )
    }
}
 
export default CircleChart
