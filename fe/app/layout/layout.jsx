import React, {Component} from 'react'
import ReactDOM from 'react-dom';
import './layout.css';

class Header extends Component {
    render() {
        return (
          <div className ="body">
              <header>
                    <div className="top">
                        <div className="flax">
                            <div>마크</div>
                            <div>메뉴 1</div>
                            <div>메뉴 2</div>
                            <div>메뉴 3</div>
                            <div>메뉴 4</div>
                            <div>아이콘 </div>
                            <div> 로그인 </div>
                        </div>
                    </div>
              </header>
              <content>
                  <div>내용</div>
              </content>

              <footer>
                  <div> footer</div>
              </footer>
          </div>
        );
      }
}


ReactDOM.render(<Header/>,document.getElementById('test'));
