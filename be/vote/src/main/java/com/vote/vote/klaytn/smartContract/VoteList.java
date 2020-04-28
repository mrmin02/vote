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
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated smart contract code.
 * <p><strong>Do not modify!</strong>
 */
public class VoteList extends SmartContract {
    private static final String BINARY = "608060405260006002556003805534801561001957600080fd5b50600080546001600160a01b031916331781556001556105498061003e6000396000f3fe6080604052600436106100c25760003560e01c80638da5cb5b1161007f578063b8f6860c11610059578063b8f6860c1461028e578063d0e30db0146102c4578063de8fa431146102cc578063e9ab77e5146102e1576100c2565b80638da5cb5b14610212578063a87d942c14610243578063b36d8a9f14610258576100c2565b806312065fe0146100c457806312514bba146100eb578063170ab40514610129578063224348361461015357806350c42c78146101835780637cd912c8146101e8575b005b3480156100d057600080fd5b506100d961030f565b60408051918252519081900360200190f35b3480156100f757600080fd5b506101156004803603602081101561010e57600080fd5b5035610314565b604080519115158252519081900360200190f35b34801561013557600080fd5b506101156004803603602081101561014c57600080fd5b5035610360565b34801561015f57600080fd5b506101156004803603604081101561017657600080fd5b50803590602001356103ab565b34801561018f57600080fd5b506101986103b9565b60408051602080825283518183015283519192839290830191858101910280838360005b838110156101d45781810151838201526020016101bc565b505050509050019250505060405180910390f35b3480156101f457600080fd5b506101156004803603602081101561020b57600080fd5b5035610411565b34801561021e57600080fd5b50610227610444565b604080516001600160a01b039092168252519081900360200190f35b34801561024f57600080fd5b506100d9610453565b34801561026457600080fd5b506101156004803603606081101561027b57600080fd5b5080359060208101359060400135610459565b34801561029a57600080fd5b50610115600480360360608110156102b157600080fd5b508035906020810135906040013561047c565b6100c26104f4565b3480156102d857600080fd5b506100d961050d565b3480156102ed57600080fd5b506102f6610513565b6040805192835260208301919091528051918290030190f35b303190565b60008161031f61030f565b101561032a57600080fd5b604051339083156108fc029084906000818181858888f19350505050158015610357573d6000803e3d6000fd5b50600192915050565b60005b60045482146103a3576004805460018101825560009182527f8a35acfbc15ff81a39ae7d344fd709f28e8600b4aa8c65c6b64bfe7fe36bd19b0155610363565b506001919050565b600291909155600355600190565b6060600480548060200260200160405190810160405280929190818152602001828054801561040757602002820191906000526020600020905b8154815260200190600101908083116103f3575b5050505050905090565b60006004600183038154811061042357fe5b60009182526020909120018054600190810190915580548101815592915050565b6000546001600160a01b031681565b60015490565b600061046584846103ab565b5061046f82610360565b50600190505b9392505050565b6000816002541115801561049257506003548211155b156104ec57836104a061030f565b10156104ab57600080fd5b604051339085156108fc029086906000818181858888f193505050501580156104d8573d6000803e3d6000fd5b506104e283610411565b5060019050610475565b506000610475565b6000546001600160a01b0316331461050b57600080fd5b565b60045490565b600254600354909156fea165627a7a723058202616e4d031bb104b9e256356ab8b70543e36a631c283851c02b79953d7c7f6450029";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_SETSIZE = "setSize";

    public static final String FUNC_SETTIMES = "setTimes";

    public static final String FUNC_GETLISTVOTE = "getListVote";

    public static final String FUNC_LISTVOTE = "listVote";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETCOUNT = "getCount";

    public static final String FUNC_SETOPTIONS = "setOptions";

    public static final String FUNC_TRANSFERWITHDATA = "transferWithData";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_GETSIZE = "getSize";

    public static final String FUNC_GETTIMES = "getTimes";

    protected VoteList(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    protected VoteList(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> setTimes(BigInteger _startTime, BigInteger _endTime) {
        final Function function = new Function(
                FUNC_SETTIMES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.web3j.abi.datatypes.generated.Uint256(_endTime)), 
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

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> listVote(BigInteger _num) {
        final Function function = new Function(
                FUNC_LISTVOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_num)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getCount() {
        final Function function = new Function(FUNC_GETCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> transferWithData(BigInteger _value, BigInteger _vint, BigInteger _time) {
        final Function function = new Function(
                FUNC_TRANSFERWITHDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.generated.Uint256(_vint), 
                new org.web3j.abi.datatypes.generated.Uint256(_time)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public static VoteList load(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return new VoteList(contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    public static VoteList load(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VoteList(contractAddress, caver, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VoteList> deploy(Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VoteList.class, caver, credentials, chainId, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<VoteList> deploy(Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(VoteList.class, caver, transactionManager, contractGasProvider, BINARY, "");
    }
}