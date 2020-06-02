package com.vote.vote.klaytn.smartContract;

import com.klaytn.caver.Caver;
import com.klaytn.caver.crpyto.KlayCredentials;
import com.klaytn.caver.methods.response.KlayTransactionReceipt;
import com.klaytn.caver.tx.SmartContract;
import com.klaytn.caver.tx.manager.TransactionManager;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated smart contract code.
 * <p><strong>Do not modify!</strong>
 */
public class RIROVote3 extends SmartContract {
    private static final String BINARY = "60806040526001805560006002556003805534801561001d57600080fd5b50600080546001600160a01b031916331781556001556110df806100426000396000f3fe6080604052600436106100f35760003560e01c80639bd575c21161008a578063b5aa89eb11610059578063b5aa89eb1461028f578063d0e30db0146102b9578063de8fa431146102c1578063e9ab77e5146102d6576100f3565b80639bd575c214610211578063a87d942c14610234578063a998590c14610249578063b36d8a9f1461026f576100f3565b806322434836116100c6578063224348361461018d57806331841c23146101ad57806350c42c78146101cd5780638da5cb5b146101ef576100f3565b806312065fe0146100f557806312514bba14610120578063170ab4051461014d57806318e7caf21461016d575b005b34801561010157600080fd5b5061010a6102f9565b604051610117919061104a565b60405180910390f35b34801561012c57600080fd5b5061014061013b366004610d24565b6102fe565b604051610117919061103c565b34801561015957600080fd5b50610140610168366004610d24565b61034a565b34801561017957600080fd5b50610140610188366004610d84565b61055c565b34801561019957600080fd5b506101406101a8366004610d4a565b610691565b3480156101b957600080fd5b506101406101c8366004610dd1565b6106a4565b3480156101d957600080fd5b506101e2610723565b6040516101179190610ede565b3480156101fb57600080fd5b5061020461077b565b6040516101179190610ed0565b34801561021d57600080fd5b5061022661078a565b604051610117929190610eef565b34801561024057600080fd5b5061010a61083d565b34801561025557600080fd5b5061025e610843565b604051610117959493929190610f14565b34801561027b57600080fd5b5061014061028a366004610d84565b610a00565b34801561029b57600080fd5b506102a4610a21565b60405161011799989796959493929190610f80565b6100f3610cef565b3480156102cd57600080fd5b5061010a610d08565b3480156102e257600080fd5b506102eb610d0e565b604051610117929190611058565b303190565b6000816103096102f9565b101561031457600080fd5b604051339083156108fc029084906000818181858888f19350505050158015610341573d6000803e3d6000fd5b50600192915050565b60005b600454821461038d576004805460018101825560009182527f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b015561034d565b5b60055482146103ce576005805460018101825560009182527f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db0015561038e565b5b600654821461040f576006805460018101825560009182527ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f01556103cf565b5b6007548214610450576007805460018101825560009182527fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c6880155610410565b5b6008548214610491576008805460018101825560009182527ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee30155610451565b5b60095482146104d2576009805460018101825560009182527f6e1540171b6c0c960b71a7020d9f60077f6af931a8bbf590da0223dacf75c7af0155610492565b5b600a54821461051357600a805460018101825560009182527fc65a7bb8d6351c1cf70c95a316cc6a92839c986682d98bc35f958f4883f9d2a801556104d3565b5b600b54821461055457600b805460018101825560009182527f0175b7a638427703f0dbe7bb9bbf987a2551717b34e79f33b5b1008d1fa01db90155610514565b506001919050565b60006004600185038154811061056e57fe5b6000918252602090912001805460019081019091558314156105b1576005600185038154811061059a57fe5b600091825260209091200180546001019055610628565b82600214156105ca576006600185038154811061059a57fe5b82600314156105e3576007600185038154811061059a57fe5b82600414156105fc576008600185038154811061059a57fe5b8260051415610628576009600185038154811061061557fe5b6000918252602090912001805460010190555b8161065457600a600185038154811061063d57fe5b600091825260209091200180546001019055610680565b816001141561068057600b600185038154811061066d57fe5b6000918252602090912001805460010190555b5060018054810181555b9392505050565b6002829055600381905560015b92915050565b600084600254111580156106ba57506003548511155b1561071657856106c86102f9565b10156106d357600080fd5b604051339087156108fc029088906000818181858888f19350505050158015610700573d6000803e3d6000fd5b5061070c82858561055c565b506001905061071a565b5060005b95945050505050565b6060600480548060200260200160405190810160405280929190818152602001828054801561077157602002820191906000526020600020905b81548152602001906001019080831161075d575b5050505050905090565b6000546001600160a01b031681565b606080600a600b818054806020026020016040519081016040528092919081815260200182805480156107dc57602002820191906000526020600020905b8154815260200190600101908083116107c8575b505050505091508080548060200260200160405190810160405280929190818152602001828054801561082e57602002820191906000526020600020905b81548152602001906001019080831161081a575b50505050509050915091509091565b60015490565b606080606080606060056006600760086009848054806020026020016040519081016040528092919081815260200182805480156108a057602002820191906000526020600020905b81548152602001906001019080831161088c575b50505050509450838054806020026020016040519081016040528092919081815260200182805480156108f257602002820191906000526020600020905b8154815260200190600101908083116108de575b505050505093508280548060200260200160405190810160405280929190818152602001828054801561094457602002820191906000526020600020905b815481526020019060010190808311610930575b505050505092508180548060200260200160405190810160405280929190818152602001828054801561099657602002820191906000526020600020905b815481526020019060010190808311610982575b50505050509150808054806020026020016040519081016040528092919081815260200182805480156109e857602002820191906000526020600020905b8154815260200190600101908083116109d4575b50505050509050945094509450945094509091929394565b6000610a0c8484610691565b50610a168261034a565b506001949350505050565b6060806060806060806060806000600460056006600760086009600a600b60015488805480602002602001604051908101604052809291908181526020018280548015610a8d57602002820191906000526020600020905b815481526020019060010190808311610a79575b5050505050985087805480602002602001604051908101604052809291908181526020018280548015610adf57602002820191906000526020600020905b815481526020019060010190808311610acb575b5050505050975086805480602002602001604051908101604052809291908181526020018280548015610b3157602002820191906000526020600020905b815481526020019060010190808311610b1d575b5050505050965085805480602002602001604051908101604052809291908181526020018280548015610b8357602002820191906000526020600020905b815481526020019060010190808311610b6f575b5050505050955084805480602002602001604051908101604052809291908181526020018280548015610bd557602002820191906000526020600020905b815481526020019060010190808311610bc1575b5050505050945083805480602002602001604051908101604052809291908181526020018280548015610c2757602002820191906000526020600020905b815481526020019060010190808311610c13575b5050505050935082805480602002602001604051908101604052809291908181526020018280548015610c7957602002820191906000526020600020905b815481526020019060010190808311610c65575b5050505050925081805480602002602001604051908101604052809291908181526020018280548015610ccb57602002820191906000526020600020905b815481526020019060010190808311610cb7575b50505050509150985098509850985098509850985098509850909192939495969798565b6000546001600160a01b03163314610d0657600080fd5b565b60045490565b6002546003549091565b600061068a82356110a2565b600060208284031215610d3657600080fd5b6000610d428484610d18565b949350505050565b60008060408385031215610d5d57600080fd5b6000610d698585610d18565b9250506020610d7a85828601610d18565b9150509250929050565b600080600060608486031215610d9957600080fd5b6000610da58686610d18565b9350506020610db686828701610d18565b9250506040610dc786828701610d18565b9150509250925092565b600080600080600060a08688031215610de957600080fd5b6000610df58888610d18565b9550506020610e0688828901610d18565b9450506040610e1788828901610d18565b9350506060610e2888828901610d18565b9250506080610e3988828901610d18565b9150509295509295909350565b6000610e528383610ec7565b505060200190565b610e6381611086565b82525050565b6000610e7482611079565b610e7e818561107d565b9350610e8983611073565b60005b82811015610eb457610e9f868351610e46565b9550610eaa82611073565b9150600101610e8c565b5093949350505050565b610e6381611091565b610e63816110a2565b6020810161069e8284610e5a565b6020808252810161068a8184610e69565b60408082528101610f008185610e69565b90508181036020830152610d428184610e69565b60a08082528101610f258188610e69565b90508181036020830152610f398187610e69565b90508181036040830152610f4d8186610e69565b90508181036060830152610f618185610e69565b90508181036080830152610f758184610e69565b979650505050505050565b6101208082528101610f92818c610e69565b90508181036020830152610fa6818b610e69565b90508181036040830152610fba818a610e69565b90508181036060830152610fce8189610e69565b90508181036080830152610fe28188610e69565b905081810360a0830152610ff68187610e69565b905081810360c083015261100a8186610e69565b905081810360e083015261101e8185610e69565b905061102e610100830184610ec7565b9a9950505050505050505050565b6020810161069e8284610ebe565b6020810161069e8284610ec7565b604081016110668285610ec7565b61068a6020830184610ec7565b60200190565b5190565b90815260200190565b600061069e82611096565b151590565b6001600160a01b031690565b9056fea265627a7a723058205d5f09f4bc68fa47d30cad826aab4c301e2b81ef8a89eae7fea9b972bf5ad2a66c6578706572696d656e74616cf50037";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_SETSIZE = "setSize";

    public static final String FUNC_LISTVOTE = "listVote";

    public static final String FUNC_SETTIMES = "setTimes";

    public static final String FUNC_TRANSFERWITHDATA = "transferWithData";

    public static final String FUNC_GETLISTVOTE = "getListVote";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETLISTGENDER = "getListGender";

    public static final String FUNC_GETCOUNT = "getCount";

    public static final String FUNC_GETLISTAGE = "getListAge";

    public static final String FUNC_SETOPTIONS = "setOptions";

    public static final String FUNC_GETLISTTOTAL = "getListTotal";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_GETSIZE = "getSize";

    public static final String FUNC_GETTIMES = "getTimes";

    protected RIROVote3(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    protected RIROVote3(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, caver, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getBalance() {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> transfer(BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setSize(BigInteger _limit) {
        final Function function = new Function(
                FUNC_SETSIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> listVote(BigInteger _num, BigInteger _uAge, BigInteger _uGender) {
        final Function function = new Function(
                FUNC_LISTVOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_num), 
                new org.web3j.abi.datatypes.generated.Uint256(_uAge), 
                new org.web3j.abi.datatypes.generated.Uint256(_uGender)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setTimes(BigInteger _startTime, BigInteger _endTime) {
        final Function function = new Function(
                FUNC_SETTIMES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_endTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> transferWithData(BigInteger _value, BigInteger _time, BigInteger _uAge, BigInteger _uGender, BigInteger _vint) {
        final Function function = new Function(
                FUNC_TRANSFERWITHDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.generated.Uint256(_time), 
                new org.web3j.abi.datatypes.generated.Uint256(_uAge), 
                new org.web3j.abi.datatypes.generated.Uint256(_uGender), 
                new org.web3j.abi.datatypes.generated.Uint256(_vint)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getListVote() {
        final Function function = new Function(FUNC_GETLISTVOTE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<List<BigInteger>, List<BigInteger>>> getListGender() {
        final Function function = new Function(FUNC_GETLISTGENDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<Tuple2<List<BigInteger>, List<BigInteger>>>(
                new Callable<Tuple2<List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple2<List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteCall<BigInteger> getCount() {
        final Function function = new Function(FUNC_GETCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>> getListAge() {
        final Function function = new Function(FUNC_GETLISTAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>(
                new Callable<Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()), 
                                convertToNative((List<Uint256>) results.get(3).getValue()), 
                                convertToNative((List<Uint256>) results.get(4).getValue()));
                    }
                });
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setOptions(BigInteger _startTime, BigInteger _endTime, BigInteger _limit) {
        final Function function = new Function(
                FUNC_SETOPTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_endTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_limit)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger>> getListTotal() {
        final Function function = new Function(FUNC_GETLISTTOTAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger>>(
                new Callable<Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger>>() {
                    @Override
                    public Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple9<List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>, BigInteger>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()), 
                                convertToNative((List<Uint256>) results.get(3).getValue()), 
                                convertToNative((List<Uint256>) results.get(4).getValue()), 
                                convertToNative((List<Uint256>) results.get(5).getValue()), 
                                convertToNative((List<Uint256>) results.get(6).getValue()), 
                                convertToNative((List<Uint256>) results.get(7).getValue()), 
                                (BigInteger) results.get(8).getValue());
                    }
                });
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> deposit(BigInteger pebValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, pebValue);
    }

    public RemoteCall<BigInteger> getSize() {
        final Function function = new Function(FUNC_GETSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> getTimes() {
        final Function function = new Function(FUNC_GETTIMES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public static RIROVote3 load(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return new RIROVote3(contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    public static RIROVote3 load(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RIROVote3(contractAddress, caver, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RIROVote3> deploy(Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RIROVote3.class, caver, credentials, chainId, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<RIROVote3> deploy(Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RIROVote3.class, caver, transactionManager, contractGasProvider, BINARY, "");
    }
}
