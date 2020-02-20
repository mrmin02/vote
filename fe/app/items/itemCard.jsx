import React, { Component } from 'react';
import Button from '@bit/react-bootstrap.react-bootstrap.button';
import Card from '@bit/react-bootstrap.react-bootstrap.card';
import ReactBootstrapStyle from '@bit/react-bootstrap.react-bootstrap.internal.style-links';
 
class Item extends Component {
    constructor(props){
        super(props)
    }
	render() {
		return (
			<Card style={{ width: '18rem' }}>
				<Card.Img variant="top" src={'/uploads/'+this.props.img} />
				<Card.Body>
                <Card.Title>{this.props.name}</Card.Title>
					<Card.Text>
						소개 추가 가능
    				</Card.Text>
					<Button variant="primary">투표하기</Button>
				</Card.Body>
			</Card>
		)
	}
}
function ItemCard(img,name){
    return(
        <div>
            <ReactBootstrapStyle />
            <ItemCard img={img} name={name}/>
        </div>
    )
}

export default ItemCard;