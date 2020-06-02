// package com.vote.vote.klaytn.smartContract;

// import com.klaytn.caver.Caver;
// import com.klaytn.caver.crpyto.KlayCredentials;
// import com.klaytn.caver.methods.response.KlayTransactionReceipt;
// import com.klaytn.caver.tx.SmartContract;
// import com.klaytn.caver.tx.manager.TransactionManager;
// import java.math.BigInteger;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.List;
// import java.util.concurrent.Callable;
// import org.web3j.abi.TypeReference;
// import org.web3j.abi.datatypes.Address;
// import org.web3j.abi.datatypes.DynamicArray;
// import org.web3j.abi.datatypes.Function;
// import org.web3j.abi.datatypes.Type;
// import org.web3j.abi.datatypes.generated.Uint256;
// import org.web3j.protocol.core.RemoteCall;
// import org.web3j.tuples.generated.Tuple2;
// import org.web3j.tuples.generated.Tuple4;
// import org.web3j.tx.gas.ContractGasProvider;

// /**
//  * <p>Auto generated smart contract code.
//  * <p><strong>Do not modify!</strong>
//  */
// public class RIROVote extends SmartContract {
//     private static final String BINARY = "608060405260006001556000600255600380556000600455600460055534801561002857600080fd5b50600080546001600160a01b03191633178155600155610d598061004d6000396000f3fe6080604052600436106100fe5760003560e01c80639bd575c211610095578063b5aa89eb11610064578063b5aa89eb14610288578063d0e30db0146102ad578063de8fa431146102b5578063e9ab77e5146102ca578063edbe3baf146102ed576100fe565b80639bd575c21461021c578063a52a7fbc1461023e578063a87d942c1461025e578063a998590c14610273576100fe565b806322434836116100d1578063224348361461019857806331841c23146101b857806350c42c78146101d85780638da5cb5b146101fa576100fe565b806312065fe01461010057806312514bba1461012b578063170ab4051461015857806318e7caf214610178575b005b34801561010c57600080fd5b5061011561030d565b6040516101229190610cc7565b60405180910390f35b34801561013757600080fd5b5061014b6101463660046109d4565b610313565b6040516101229190610cb9565b34801561016457600080fd5b5061014b6101733660046109d4565b61035f565b34801561018457600080fd5b5061014b610193366004610a34565b61048f565b3480156101a457600080fd5b5061014b6101b33660046109fa565b61053b565b3480156101c457600080fd5b5061014b6101d3366004610a81565b61054e565b3480156101e457600080fd5b506101ed6105cd565b6040516101229190610c60565b34801561020657600080fd5b5061020f610625565b6040516101229190610c41565b34801561022857600080fd5b50610231610634565b6040516101229190610c4f565b34801561024a57600080fd5b5061014b6102593660046109fa565b6106cd565b34801561026a57600080fd5b506101156106db565b34801561027f57600080fd5b506102316106e1565b34801561029457600080fd5b5061029d610771565b6040516101229493929190610c71565b6100fe610907565b3480156102c157600080fd5b50610115610920565b3480156102d657600080fd5b506102df610926565b604051610122929190610cd5565b3480156102f957600080fd5b5061014b610308366004610a81565b610930565b30315b90565b60008161031e61030d565b101561032957600080fd5b604051339083156108fc029084906000818181858888f19350505050158015610356573d6000803e3d6000fd5b50600192915050565b60005b60065482146103a2576006805460018101825560009182527ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f0155610362565b60005b600654811015610423576040805160a081018252600080825260208201819052918101829052606081018290526080810182905260078054600181018083559190935291610419917fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c68890910190600561095e565b50506001016103a5565b5060005b600654811015610356576040805180820190915260008082526020820181905260088054600181018083559190925291610485917ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee30190600261095e565b5050600101610427565b6000600660018503815481106104a157fe5b6000918252602090912001805460010190556007805460001986019081106104c557fe5b9060005260206000200160018403815481106104dd57fe5b60009182526020909120018054600101905560088054600019860190811061050157fe5b90600052602060002001828154811061051657fe5b60009182526020909120018054600190810190915580548101815590505b9392505050565b6002829055600381905560015b92915050565b6000846002541115801561056457506003548511155b156105c0578561057261030d565b101561057d57600080fd5b604051339087156108fc029088906000818181858888f193505050501580156105aa573d6000803e3d6000fd5b506105b682858561048f565b50600190506105c4565b5060005b95945050505050565b6060600680548060200260200160405190810160405280929190818152602001828054801561061b57602002820191906000526020600020905b815481526020019060010190808311610607575b5050505050905090565b6000546001600160a01b031681565b60606008805480602002602001604051908101604052809291908181526020016000905b828210156106c4576000848152602090819020830180546040805182850281018501909152818152928301828280156106b057602002820191906000526020600020905b81548152602001906001019080831161069c575b505050505081526020019060010190610658565b50505050905090565b600491909155600555600090565b60015490565b60606007805480602002602001604051908101604052809291908181526020016000905b828210156106c45760008481526020908190208301805460408051828502810185019091528181529283018282801561075d57602002820191906000526020600020905b815481526020019060010190808311610749575b505050505081526020019060010190610705565b60608060606000600660076008600154838054806020026020016040519081016040528092919081815260200182805480156107cc57602002820191906000526020600020905b8154815260200190600101908083116107b8575b5050505050935082805480602002602001604051908101604052809291908181526020016000905b828210156108605760008481526020908190208301805460408051828502810185019091528181529283018282801561084c57602002820191906000526020600020905b815481526020019060010190808311610838575b5050505050815260200190600101906107f4565b50505050925081805480602002602001604051908101604052809291908181526020016000905b828210156108f3576000848152602090819020830180546040805182850281018501909152818152928301828280156108df57602002820191906000526020600020905b8154815260200190600101908083116108cb575b505050505081526020019060010190610887565b505050509150935093509350935090919293565b6000546001600160a01b0316331461091e57600080fd5b565b60065490565b6002546003549091565b600061093c868661053b565b5061094783836106cd565b506109518461035f565b5060019695505050505050565b82805482825590600052602060002090810192821561099e579160200282015b8281111561099e578251829060ff1690559160200191906001019061097e565b506109aa9291506109ae565b5090565b61031091905b808211156109aa57600081556001016109b4565b60006105348235610310565b6000602082840312156109e657600080fd5b60006109f284846109c8565b949350505050565b60008060408385031215610a0d57600080fd5b6000610a1985856109c8565b9250506020610a2a858286016109c8565b9150509250929050565b600080600060608486031215610a4957600080fd5b6000610a5586866109c8565b9350506020610a66868287016109c8565b9250506040610a77868287016109c8565b9150509250925092565b600080600080600060a08688031215610a9957600080fd5b6000610aa588886109c8565b9550506020610ab6888289016109c8565b9450506040610ac7888289016109c8565b9350506060610ad8888289016109c8565b9250506080610ae9888289016109c8565b9150509295509295909350565b60006105348383610be4565b6000610b0e8383610c38565b505060200190565b610b1f81610d03565b82525050565b6000610b3082610cf6565b610b3a8185610cfa565b935083602082028501610b4c85610cf0565b60005b84811015610b83578383038852610b67838351610af6565b9250610b7282610cf0565b602098909801979150600101610b4f565b50909695505050505050565b6000610b9a82610cf6565b610ba48185610cfa565b9350610baf83610cf0565b60005b82811015610bda57610bc5868351610b02565b9550610bd082610cf0565b9150600101610bb2565b5093949350505050565b6000610bef82610cf6565b610bf98185610cfa565b9350610c0483610cf0565b60005b82811015610bda57610c1a868351610b02565b9550610c2582610cf0565b9150600101610c07565b610b1f81610d0e565b610b1f81610310565b602081016105488284610b16565b602080825281016105348184610b25565b602080825281016105348184610b8f565b60808082528101610c828187610b8f565b90508181036020830152610c968186610b25565b90508181036040830152610caa8185610b25565b90506105c46060830184610c38565b602081016105488284610c2f565b602081016105488284610c38565b60408101610ce38285610c38565b6105346020830184610c38565b60200190565b5190565b90815260200190565b600061054882610d13565b151590565b6001600160a01b03169056fea265627a7a72305820dced05f899ba0734f954ee022de5a93fb6a8a1e71f6580ede09311a73fe7237b6c6578706572696d656e74616cf50037";

//     public static final String FUNC_GETBALANCE = "getBalance";

//     public static final String FUNC_TRANSFER = "transfer";

//     public static final String FUNC_SETSIZE = "setSize";

//     public static final String FUNC_LISTVOTE = "listVote";

//     public static final String FUNC_SETTIMES = "setTimes";

//     public static final String FUNC_TRANSFERWITHDATA = "transferWithData";

//     public static final String FUNC_GETLISTVOTE = "getListVote";

//     public static final String FUNC_OWNER = "owner";

//     public static final String FUNC_GETLISTGENDER = "getListGender";

//     public static final String FUNC_SETRESULTSTATUS = "setResultStatus";

//     public static final String FUNC_GETCOUNT = "getCount";

//     public static final String FUNC_GETLISTAGE = "getListAge";

//     public static final String FUNC_GETLISTTOTAL = "getListTotal";

//     public static final String FUNC_DEPOSIT = "deposit";

//     public static final String FUNC_GETSIZE = "getSize";

//     public static final String FUNC_GETTIMES = "getTimes";

//     public static final String FUNC_SETOPTIONS = "setOptions";

//     protected RIROVote(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
//         super(BINARY, contractAddress, caver, credentials, chainId, contractGasProvider);
//     }

//     protected RIROVote(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//         super(BINARY, contractAddress, caver, transactionManager, contractGasProvider);
//     }

//     public RemoteCall<BigInteger> getBalance() {
//         final Function function = new Function(FUNC_GETBALANCE, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//         return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> transfer(BigInteger _value) {
//         final Function function = new Function(
//                 FUNC_TRANSFER, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setSize(BigInteger _limit) {
//         final Function function = new Function(
//                 FUNC_SETSIZE, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_limit)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> listVote(BigInteger _num, BigInteger _uAge, BigInteger _uGender) {
//         final Function function = new Function(
//                 FUNC_LISTVOTE, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_num), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_uAge), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_uGender)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setTimes(BigInteger _startTime, BigInteger _endTime) {
//         final Function function = new Function(
//                 FUNC_SETTIMES, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_endTime)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> transferWithData(BigInteger _value, BigInteger _time, BigInteger _uAge, BigInteger _uGender, BigInteger _vint) {
//         final Function function = new Function(
//                 FUNC_TRANSFERWITHDATA, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_time), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_uAge), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_uGender), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_vint)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<List> getListVote() {
//         final Function function = new Function(FUNC_GETLISTVOTE, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
//         return new RemoteCall<List>(
//                 new Callable<List>() {
//                     @Override
//                     @SuppressWarnings("unchecked")
//                     public List call() throws Exception {
//                         List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
//                         return convertToNative(result);
//                     }
//                 });
//     }

//     public RemoteCall<String> owner() {
//         final Function function = new Function(FUNC_OWNER, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
//         return executeRemoteCallSingleValueReturn(function, String.class);
//     }

//     public RemoteCall<List> getListGender() {
//         final Function function = new Function(FUNC_GETLISTGENDER, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}));
//         return new RemoteCall<List>(
//                 new Callable<List>() {
//                     @Override
//                     @SuppressWarnings("unchecked")
//                     public List call() throws Exception {
//                         List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
//                         return convertToNative(result);
//                     }
//                 });
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setResultStatus(BigInteger _state, BigInteger _resultTime) {
//         final Function function = new Function(
//                 FUNC_SETRESULTSTATUS, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_state), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_resultTime)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public RemoteCall<BigInteger> getCount() {
//         final Function function = new Function(FUNC_GETCOUNT, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//         return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//     }

//     public RemoteCall<List> getListAge() {
//         final Function function = new Function(FUNC_GETLISTAGE, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}));
//         return new RemoteCall<List>(
//                 new Callable<List>() {
//                     @Override
//                     @SuppressWarnings("unchecked")
//                     public List call() throws Exception {
//                         List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
//                         return convertToNative(result);
//                     }
//                 });
//     }

//     public RemoteCall<Tuple4<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>, BigInteger>> getListTotal() {
//         final Function function = new Function(FUNC_GETLISTTOTAL, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}, new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}, new TypeReference<Uint256>() {}));
//         return new RemoteCall<Tuple4<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>, BigInteger>>(
//                 new Callable<Tuple4<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>, BigInteger>>() {
//                     @Override
//                     public Tuple4<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>, BigInteger> call() throws Exception {
//                         List<Type> results = executeCallMultipleValueReturn(function);
//                         return new Tuple4<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>, BigInteger>(
//                                 convertToNative((List<Uint256>) results.get(0).getValue()), 
//                                 convertToNative((List<DynamicArray<Uint256>>) results.get(1).getValue()), 
//                                 convertToNative((List<DynamicArray<Uint256>>) results.get(2).getValue()), 
//                                 (BigInteger) results.get(3).getValue());
//                     }
//                 });
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> deposit(BigInteger pebValue) {
//         final Function function = new Function(
//                 FUNC_DEPOSIT, 
//                 Arrays.<Type>asList(), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function, pebValue);
//     }

//     public RemoteCall<BigInteger> getSize() {
//         final Function function = new Function(FUNC_GETSIZE, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//         return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//     }

//     public RemoteCall<Tuple2<BigInteger, BigInteger>> getTimes() {
//         final Function function = new Function(FUNC_GETTIMES, 
//                 Arrays.<Type>asList(), 
//                 Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
//         return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
//                 new Callable<Tuple2<BigInteger, BigInteger>>() {
//                     @Override
//                     public Tuple2<BigInteger, BigInteger> call() throws Exception {
//                         List<Type> results = executeCallMultipleValueReturn(function);
//                         return new Tuple2<BigInteger, BigInteger>(
//                                 (BigInteger) results.get(0).getValue(), 
//                                 (BigInteger) results.get(1).getValue());
//                     }
//                 });
//     }

//     public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setOptions(BigInteger _startTime, BigInteger _endTime, BigInteger _limit, BigInteger _state, BigInteger _resultTime) {
//         final Function function = new Function(
//                 FUNC_SETOPTIONS, 
//                 Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_endTime), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_limit), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_state), 
//                 new org.web3j.abi.datatypes.generated.Uint256(_resultTime)), 
//                 Collections.<TypeReference<?>>emptyList());
//         return executeRemoteCallTransaction(function);
//     }

//     public static RIROVote load(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
//         return new RIROVote(contractAddress, caver, credentials, chainId, contractGasProvider);
//     }

//     public static RIROVote load(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//         return new RIROVote(contractAddress, caver, transactionManager, contractGasProvider);
//     }

//     public static RemoteCall<RIROVote> deploy(Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
//         return deployRemoteCall(RIROVote.class, caver, credentials, chainId, contractGasProvider, BINARY, "");
//     }

//     public static RemoteCall<RIROVote> deploy(Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//         return deployRemoteCall(RIROVote.class, caver, transactionManager, contractGasProvider, BINARY, "");
//     }
// }
