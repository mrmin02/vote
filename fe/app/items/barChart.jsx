import React from 'react';
import Chart from '@bit/nexxtway.react-rainbow.chart';
import Dataset from '@bit/nexxtway.react-rainbow.dataset';
// import ButtonGroup from '@bit/nexxtway.react-rainbow.button-group';
// import Button from '@bit/nexxtway.react-rainbow.button';
 
const containerStyles = {
  width: 600,
};

// const { FontAwesomeIcon } = require('@fortawesome/react-fontawesome');
// const { faPlus, faMinus } = require('@fortawesome/free-solid-svg-icons');

export default class BarChart extends React.Component {
    constructor(props) {
        super(props);
        // this.titles =  ['10','20','30','40','50','60'];// 후보
        // this.colors = ['#FA5858', '#FE9A2E', '#F7FE2E','#58ACFA', '#D358F7'];
        // this.months = ['July', 'August', 'September', 'October', 'November', 'December'];

        // props.data = {
        //     labels: ['10','20','30','40','50','60'],// 후보 이름.
        //     datasets: [
        //         {
        //             title: '10대',
        //             backgroundColor: '#FA5858',
        //             values: [1,1,1,1,1],
        //         },
        //         {
        //             title: '20대',
        //             backgroundColor: '#FE9A2E',
        //             values: [1,1,1,1,1],
        //         },
        //         {
        //             title: '30대',
        //             backgroundColor: '#F7FE2E',
        //             values: [1,1,1,1,1],
        //         },
        //         {
        //             title: '40대',
        //             backgroundColor: '#58ACFA',
        //             values: [1,1,1,1,1],
        //         },
		// 						{
        //             title: '50上',
        //             backgroundColor: '#D358F7',
        //             values: [1,1,1,1,1],
        //         },
        //     ],
        // };
    }


    renderDatasets() {
        const { datasets } = this.props.data;
        return datasets.map(({ title, values, backgroundColor }) => (
            <Dataset key={title} title={title} values={values} backgroundColor={backgroundColor} />
        ));
    }

    render() {
        const { labels, datasets } = this.props.data;

        return (
            <div>
                <div style={containerStyles}>
                    <Chart labels={labels} type="bar">
                        {this.renderDatasets()}
                    </Chart>
                </div>
            </div>
        );
    }
}
