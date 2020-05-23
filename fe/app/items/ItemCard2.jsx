import React, { Component } from 'react';
import Card from '@bit/jakeprins.react-milkshake.card';

class ItemCard2 extends Component{
    constructor(props){
		super(props);
		console.log(this.props)
    }

    render(){
        return (
            <div style={{margin: 20, maxWidth:300}}>
                <Card
                    image={'/uploads/'+this.props.img}
                    title={this.props.name}
                    text=' 설명 입니다. '
                />
            </div>
        )
        
    }
}

export default ItemCard2