import React, { Component } from 'react';
import Card from '@bit/jakeprins.react-milkshake.card';
import { ProgressBar, Progress } from '@bit/digiaonline.react-foundation.progress-bar';
import { Colors } from '@bit/digiaonline.react-foundation.internal.enums';

class ItemCard2 extends Component{
    constructor(props){
		super(props);
		console.log(this.props)
    }

    render(){
        return (
            <div style={{margin: 20, maxWidth: 350, maxHeight: 300}}>
                <Card
                    image={'/uploads/'+this.props.img}
                    title={this.props.name}
                    text=''
                >
                <div style={{ width: 100 }}>
                    <link rel='stylesheet' type='text/css' href='https://cdnjs.cloudflare.com/ajax/libs/foundation/6.5.1/css/foundation-float.min.css' />
                    <Progress color={Colors.SECONDARY} meter={{ text: '100%' }} tabIndex='0' min={0} max={100} value={100} valuetext='25 percent' />
                </div>
                </Card>
            </div>
        )
        
    }
}

export default ItemCard2