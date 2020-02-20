import React, {Component} from 'react';
import Button from '@bit/nexxtway.react-rainbow.button';

const css = { margin: 5 }

class ColorButton extends Component {
    constructor(props){
        super(props);
    }
    
    render() {
        return (
            <div>
                <Button
                    style={css}
                    shaded
                    label="Button Brand"
                    onClick={() => alert('clicked!')}
                    variant="brand" />
	        </div>
        );
      }
}

export default ColorButton