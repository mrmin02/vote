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
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated smart contract code.
 * <p><strong>Do not modify!</strong>
 */
public class Test6 extends SmartContract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633178155600155610344806100356000396000f3fe6080604052600436106100705760003560e01c80638da5cb5b1161004e5780638da5cb5b1461013c578063a87d942c1461016d578063b971691b14610182578063d0e30db0146101b257610070565b806312065fe01461007257806312514bba146100995780633bc5de30146100d7575b005b34801561007e57600080fd5b506100876101ba565b60408051918252519081900360200190f35b3480156100a557600080fd5b506100c3600480360360208110156100bc57600080fd5b50356101bf565b604080519115158252519081900360200190f35b3480156100e357600080fd5b506100ec61020b565b60408051602080825283518183015283519192839290830191858101910280838360005b83811015610128578181015183820152602001610110565b505050509050019250505060405180910390f35b34801561014857600080fd5b50610151610263565b604080516001600160a01b039092168252519081900360200190f35b34801561017957600080fd5b50610087610272565b34801561018e57600080fd5b506100c3600480360360408110156101a557600080fd5b5080359060200135610278565b6100706102ff565b303190565b6000816101ca6101ba565b10156101d557600080fd5b604051339083156108fc029084906000818181858888f19350505050158015610202573d6000803e3d6000fd5b50600192915050565b6060600280548060200260200160405190810160405280929190818152602001828054801561025957602002820191906000526020600020905b815481526020019060010190808311610245575b5050505050905090565b6000546001600160a01b031681565b60015490565b6000826102836101ba565b101561028e57600080fd5b604051339084156108fc029085906000818181858888f193505050501580156102bb573d6000803e3d6000fd5b505060028054600181810183556000929092527f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace0182905580548101815592915050565b6000546001600160a01b0316331461031657600080fd5b56fea165627a7a72305820f98a6c60be2a025c17656a0d938de15fe8323cc3073231ca3ff963017b4a4e440029";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_GETDATA = "getData";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_GETCOUNT = "getCount";

    public static final String FUNC_TRANSFERWITHDATA = "transferWithData";

    public static final String FUNC_DEPOSIT = "deposit";

    protected Test6(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    protected Test6(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<List> getData() {
        final Function function = new Function(FUNC_GETDATA, 
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

    public RemoteCall<BigInteger> getCount() {
        final Function function = new Function(FUNC_GETCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> transferWithData(BigInteger _value, BigInteger _vint) {
        final Function function = new Function(
                FUNC_TRANSFERWITHDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.generated.Uint256(_vint)), 
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

    public static Test6 load(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return new Test6(contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    public static Test6 load(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Test6(contractAddress, caver, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Test6> deploy(Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Test6.class, caver, credentials, chainId, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Test6> deploy(Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Test6.class, caver, transactionManager, contractGasProvider, BINARY, "");
    }
}
