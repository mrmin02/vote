// pragma solidity 0.5.6;
// pragma experimental ABIEncoderV2;

// contract RIROVote{
//     address public owner;
    
//     uint private count;
    
//     uint private startTime = 0;
//     uint private endTime = 3;
    
//     uint private showState = 0; // 0: can show /  1: cant show , during vote
//     uint private showResultTime= 4;// can show result time , after vote
    
    
//     uint[] private list;
//     uint[][] private age; // 0 ~ 4  10 ,20 ... over 50
//     uint[][] private gender; // 0,1  /  man, girl
    
//    uint[] private xList;
//    uint[][] private xAge;
//    uint[][] private xGender;
//    function () payable external{}
//     constructor() public {
//         owner = msg.sender;
//         count = 0;
//     }

//     function getBalance() public view returns (uint){
//         return address(this).balance;
//     }
    
//     function deposit() public payable{
//         require(msg.sender == owner);
//     }

//     function getCount() public view returns(uint){
//         return count;
//     }
//     function transfer(uint _value) public returns (bool){

//         require(getBalance()>= _value);
//         msg.sender.transfer(_value);

//         return true;
//     }
    
    
//     function transferWithData(uint _value, uint _time,uint _uAge,uint _uGender, uint _vint) public returns (bool){// 1: klay , 2: nowTime,      ...3:age, 4: gender, last: Seleted

//         if(startTime <= _time && _time <= endTime){ // during vote
            
//             require(getBalance()>= _value);
//             msg.sender.transfer(_value);
        
//             listVote(_vint, _uAge, _uGender);
//             return true;
//         }else{
//             return false;
//         }
//     }
//     function setOptions(uint _startTime, uint _endTime, uint _limit, uint _state, uint _resultTime) public returns(bool){
//         // 1: startTime, 2: endTime, 3: candidate num , 4:showState , 5 : showResultTime
//         setTimes(_startTime,_endTime);
//         setResultStatus(_state, _resultTime);
//         setSize(_limit);
//         return true;
//     }
//     function setTimes(uint _startTime, uint _endTime) public returns(bool){
//         startTime = _startTime;
//         endTime = _endTime;
//         return true;
//     }
//     function setResultStatus(uint _state, uint _resultTime) public returns(bool){
//         showState = _state;
//         showResultTime = _resultTime;
//     }
//     function getTimes() public view returns(uint _startTime, uint _endTime){
//         return(
//             startTime,endTime
//             );
//     }
//     function setSize(uint _limit) public returns(bool){
//         while(list.length != _limit){
//             list.push(0);
//         }
        
//         for(uint i=0; i<list.length; i++){// age[list.length][5]
//             age.push([0,0,0,0,0]);
//         }
//         for(uint i=0; i<list.length;i++){
//             gender.push([0,0]);
//         }
//         return true;
//     }
    
    
//     function getSize() public view returns(uint _limit){
//         return list.length;
//     }
//     function listVote(uint _num, uint _uAge, uint _uGender) public returns(bool){
//         list[_num-1]++;
//         // age[_uAge][_num-1]++;
//         // gender[_uGender][_num-1]++;
//         age[_num-1][_uAge-1]++;
//         gender[_num-1][_uGender]++;
//         count++;
        
        
        
//         return true;
//     }
//     function getListVote() public view returns(uint[] memory _list){
//         return list;
//     }
//     function getListGender() public view returns(uint[][] memory _gender){
//         return gender;
//     }
//     function getListAge() public view returns(uint[][] memory _age){
//         return age;
//     }
    
    
    
//     function getListTotal(uint _time) public view returns(uint[] memory _list, uint[][] memory _age, uint[][] memory _gender){// nowTime
        
//         // _datas= Total({list: list, age:age, gender:gender});
        
        
//         if(startTime<= _time && _time<=endTime){ // during
//             if(showState == 1){// cant show
//                 return (xList,xAge,xGender);
//             }
//         }else{
//             if(_time < showResultTime){
//             //not end
//                 return (xList,xAge,xGender);
//             }
//         }
        
        
        
//         return (list,age,gender);
        
        
//     }
    

// }