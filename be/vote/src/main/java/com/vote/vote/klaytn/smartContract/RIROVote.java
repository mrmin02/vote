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
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated smart contract code.
 * <p><strong>Do not modify!</strong>
 */
public class RIROVote extends SmartContract {
    private static final String BINARY = "60806040526000600255600380556000600455600460055534801561002357600080fd5b50600080546001600160a01b03191633178155600155611083806100486000396000f3fe6080604052600436106100fe5760003560e01c80638da5cb5b11610095578063a998590c11610064578063a998590c146102a2578063d0e30db0146102b7578063de8fa431146102bf578063e9ab77e5146102d4578063edbe3baf146102f7576100fe565b80638da5cb5b146102295780639bd575c21461024b578063a52a7fbc1461026d578063a87d942c1461028d576100fe565b806322434836116100d1578063224348361461019857806331841c23146101b857806350c42c78146101d85780637d272b22146101fa576100fe565b806312065fe01461010057806312514bba1461012b578063170ab4051461015857806318e7caf214610178575b005b34801561010c57600080fd5b50610115610317565b6040516101229190610ff1565b60405180910390f35b34801561013757600080fd5b5061014b610146366004610d0d565b61031d565b6040516101229190610fe3565b34801561016457600080fd5b5061014b610173366004610d0d565b610369565b34801561018457600080fd5b5061014b610193366004610d6d565b610499565b3480156101a457600080fd5b5061014b6101b3366004610d33565b610545565b3480156101c457600080fd5b5061014b6101d3366004610dba565b610558565b3480156101e457600080fd5b506101ed6105d7565b6040516101229190610f99565b34801561020657600080fd5b5061021a610215366004610d0d565b61062f565b60405161012293929190610faa565b34801561023557600080fd5b5061023e610af4565b6040516101229190610f7a565b34801561025757600080fd5b50610260610b03565b6040516101229190610f88565b34801561027957600080fd5b5061014b610288366004610d33565b610b9c565b34801561029957600080fd5b50610115610baa565b3480156102ae57600080fd5b50610260610bb0565b6100fe610c40565b3480156102cb57600080fd5b50610115610c59565b3480156102e057600080fd5b506102e9610c5f565b604051610122929190610fff565b34801561030357600080fd5b5061014b610312366004610dba565b610c69565b30315b90565b600081610328610317565b101561033357600080fd5b604051339083156108fc029084906000818181858888f19350505050158015610360573d6000803e3d6000fd5b50600192915050565b60005b60065482146103ac576006805460018101825560009182527ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f015561036c565b60005b60065481101561042d576040805160a081018252600080825260208201819052918101829052606081018290526080810182905260078054600181018083559190935291610423917fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c688909101906005610c97565b50506001016103af565b5060005b60065481101561036057604080518082019091526000808252602082018190526008805460018101808355919092529161048f917ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee301906002610c97565b5050600101610431565b6000600660018503815481106104ab57fe5b6000918252602090912001805460010190556007805460001986019081106104cf57fe5b9060005260206000200160018403815481106104e757fe5b60009182526020909120018054600101905560088054600019860190811061050b57fe5b90600052602060002001828154811061052057fe5b60009182526020909120018054600190810190915580548101815590505b9392505050565b6002829055600381905560015b92915050565b6000846002541115801561056e57506003548511155b156105ca578561057c610317565b101561058757600080fd5b604051339087156108fc029088906000818181858888f193505050501580156105b4573d6000803e3d6000fd5b506105c0828585610499565b50600190506105ce565b5060005b95945050505050565b6060600680548060200260200160405190810160405280929190818152602001828054801561062557602002820191906000526020600020905b815481526020019060010190808311610611575b5050505050905090565b6060806060836002541115801561064857506003548411155b156107e657600454600114156107e1576009600a600b828054806020026020016040519081016040528092919081815260200182805480156106a957602002820191906000526020600020905b815481526020019060010190808311610695575b5050505050925081805480602002602001604051908101604052809291908181526020016000905b8282101561073d5760008481526020908190208301805460408051828502810185019091528181529283018282801561072957602002820191906000526020600020905b815481526020019060010190808311610715575b5050505050815260200190600101906106d1565b50505050915080805480602002602001604051908101604052809291908181526020016000905b828210156107d0576000848152602090819020830180546040805182850281018501909152818152928301828280156107bc57602002820191906000526020600020905b8154815260200190600101908083116107a8575b505050505081526020019060010190610764565b505050509050925092509250610aed565b610968565b600554841015610968576009600a600b8280548060200260200160405190810160405280929190818152602001828054801561084157602002820191906000526020600020905b81548152602001906001019080831161082d575b5050505050925081805480602002602001604051908101604052809291908181526020016000905b828210156108d5576000848152602090819020830180546040805182850281018501909152818152928301828280156108c157602002820191906000526020600020905b8154815260200190600101908083116108ad575b505050505081526020019060010190610869565b50505050915080805480602002602001604051908101604052809291908181526020016000905b828210156107d05760008481526020908190208301805460408051828502810185019091528181529283018282801561095457602002820191906000526020600020905b815481526020019060010190808311610940575b5050505050815260200190600101906108fc565b600660076008828054806020026020016040519081016040528092919081815260200182805480156109b957602002820191906000526020600020905b8154815260200190600101908083116109a5575b5050505050925081805480602002602001604051908101604052809291908181526020016000905b82821015610a4d57600084815260209081902083018054604080518285028101850190915281815292830182828015610a3957602002820191906000526020600020905b815481526020019060010190808311610a25575b5050505050815260200190600101906109e1565b50505050915080805480602002602001604051908101604052809291908181526020016000905b82821015610ae057600084815260209081902083018054604080518285028101850190915281815292830182828015610acc57602002820191906000526020600020905b815481526020019060010190808311610ab8575b505050505081526020019060010190610a74565b5050505090509250925092505b9193909250565b6000546001600160a01b031681565b60606008805480602002602001604051908101604052809291908181526020016000905b82821015610b9357600084815260209081902083018054604080518285028101850190915281815292830182828015610b7f57602002820191906000526020600020905b815481526020019060010190808311610b6b575b505050505081526020019060010190610b27565b50505050905090565b600491909155600555600090565b60015490565b60606007805480602002602001604051908101604052809291908181526020016000905b82821015610b9357600084815260209081902083018054604080518285028101850190915281815292830182828015610c2c57602002820191906000526020600020905b815481526020019060010190808311610c18575b505050505081526020019060010190610bd4565b6000546001600160a01b03163314610c5757600080fd5b565b60065490565b6002546003549091565b6000610c758686610545565b50610c808383610b9c565b50610c8a84610369565b5060019695505050505050565b828054828255906000526020600020908101928215610cd7579160200282015b82811115610cd7578251829060ff16905591602001919060010190610cb7565b50610ce3929150610ce7565b5090565b61031a91905b80821115610ce35760008155600101610ced565b600061053e823561031a565b600060208284031215610d1f57600080fd5b6000610d2b8484610d01565b949350505050565b60008060408385031215610d4657600080fd5b6000610d528585610d01565b9250506020610d6385828601610d01565b9150509250929050565b600080600060608486031215610d8257600080fd5b6000610d8e8686610d01565b9350506020610d9f86828701610d01565b9250506040610db086828701610d01565b9150509250925092565b600080600080600060a08688031215610dd257600080fd5b6000610dde8888610d01565b9550506020610def88828901610d01565b9450506040610e0088828901610d01565b9350506060610e1188828901610d01565b9250506080610e2288828901610d01565b9150509295509295909350565b600061053e8383610f1d565b6000610e478383610f71565b505060200190565b610e588161102d565b82525050565b6000610e6982611020565b610e738185611024565b935083602082028501610e858561101a565b60005b84811015610ebc578383038852610ea0838351610e2f565b9250610eab8261101a565b602098909801979150600101610e88565b50909695505050505050565b6000610ed382611020565b610edd8185611024565b9350610ee88361101a565b60005b82811015610f1357610efe868351610e3b565b9550610f098261101a565b9150600101610eeb565b5093949350505050565b6000610f2882611020565b610f328185611024565b9350610f3d8361101a565b60005b82811015610f1357610f53868351610e3b565b9550610f5e8261101a565b9150600101610f40565b610e5881611038565b610e588161031a565b602081016105528284610e4f565b6020808252810161053e8184610e5e565b6020808252810161053e8184610ec8565b60608082528101610fbb8186610ec8565b90508181036020830152610fcf8185610e5e565b905081810360408301526105ce8184610e5e565b602081016105528284610f68565b602081016105528284610f71565b6040810161100d8285610f71565b61053e6020830184610f71565b60200190565b5190565b90815260200190565b60006105528261103d565b151590565b6001600160a01b03169056fea265627a7a723058204c509190c095a30c197492e6962df30ff17a46803e82a11202da2e1f6f1f40576c6578706572696d656e74616cf50037";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_SETSIZE = "setSize";

    public static final String FUNC_LISTVOTE = "listVote";

    public static final String FUNC_SETTIMES = "setTimes";

    public static final String FUNC_TRANSFERWITHDATA = "transferWithData";

    public static final String FUNC_GETLISTVOTE = "getListVote";

    public static final String FUNC_GETLISTTOTAL = "getListTotal";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETLISTGENDER = "getListGender";

    public static final String FUNC_SETRESULTSTATUS = "setResultStatus";

    public static final String FUNC_GETCOUNT = "getCount";

    public static final String FUNC_GETLISTAGE = "getListAge";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_GETSIZE = "getSize";

    public static final String FUNC_GETTIMES = "getTimes";

    public static final String FUNC_SETOPTIONS = "setOptions";

    protected RIROVote(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    protected RIROVote(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<Tuple3<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>>> getListTotal(BigInteger _time) {
        final Function function = new Function(FUNC_GETLISTTOTAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_time)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}, new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}));
        return new RemoteCall<Tuple3<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>>>(
                new Callable<Tuple3<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>>>() {
                    @Override
                    public Tuple3<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<List<BigInteger>, List<List<BigInteger>>, List<List<BigInteger>>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<DynamicArray<Uint256>>) results.get(1).getValue()), 
                                convertToNative((List<DynamicArray<Uint256>>) results.get(2).getValue()));
                    }
                });
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<List> getListGender() {
        final Function function = new Function(FUNC_GETLISTGENDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}));
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

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setResultStatus(BigInteger _state, BigInteger _resultTime) {
        final Function function = new Function(
                FUNC_SETRESULTSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_state), 
                new org.web3j.abi.datatypes.generated.Uint256(_resultTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getCount() {
        final Function function = new Function(FUNC_GETCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<List> getListAge() {
        final Function function = new Function(FUNC_GETLISTAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<DynamicArray<Uint256>>>() {}));
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

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setOptions(BigInteger _startTime, BigInteger _endTime, BigInteger _limit, BigInteger _state, BigInteger _resultTime) {
        final Function function = new Function(
                FUNC_SETOPTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_endTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_limit), 
                new org.web3j.abi.datatypes.generated.Uint256(_state), 
                new org.web3j.abi.datatypes.generated.Uint256(_resultTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RIROVote load(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return new RIROVote(contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    public static RIROVote load(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RIROVote(contractAddress, caver, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RIROVote> deploy(Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RIROVote.class, caver, credentials, chainId, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<RIROVote> deploy(Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RIROVote.class, caver, transactionManager, contractGasProvider, BINARY, "");
    }
}
