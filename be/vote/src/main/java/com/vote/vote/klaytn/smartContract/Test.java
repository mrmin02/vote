package com.vote.vote.klaytn.smartContract;

import com.klaytn.caver.Caver;
import com.klaytn.caver.crpyto.KlayCredentials;
import com.klaytn.caver.methods.response.KlayTransactionReceipt;
import com.klaytn.caver.tx.SmartContract;
import com.klaytn.caver.tx.manager.TransactionManager;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated smart contract code.
 * <p><strong>Do not modify!</strong>
 */
public class Test extends SmartContract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610189806100326000396000f3fe60806040526004361061003f5760003560e01c806312065fe01461004457806312514bba1461006b5780638da5cb5b146100a9578063d0e30db0146100da575b600080fd5b34801561005057600080fd5b506100596100e4565b60408051918252519081900360200190f35b34801561007757600080fd5b506100956004803603602081101561008e57600080fd5b50356100e9565b604080519115158252519081900360200190f35b3480156100b557600080fd5b506100be610135565b604080516001600160a01b039092168252519081900360200190f35b6100e2610144565b005b303190565b6000816100f46100e4565b10156100ff57600080fd5b604051339083156108fc029084906000818181858888f1935050505015801561012c573d6000803e3d6000fd5b50600192915050565b6000546001600160a01b031681565b6000546001600160a01b0316331461015b57600080fd5b56fea165627a7a7230582047adb9933b9c22623b81a293ea01e0f93af92ec6f34bd96f3ee20982d3f21ba50029";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_DEPOSIT = "deposit";

    protected Test(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    protected Test(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<KlayTransactionReceipt.TransactionReceipt> deposit(BigInteger pebValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, pebValue);
    }

    public static Test load(String contractAddress, Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return new Test(contractAddress, caver, credentials, chainId, contractGasProvider);
    }

    public static Test load(String contractAddress, Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Test(contractAddress, caver, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Test> deploy(Caver caver, KlayCredentials credentials, int chainId, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Test.class, caver, credentials, chainId, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Test> deploy(Caver caver, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Test.class, caver, transactionManager, contractGasProvider, BINARY, "");
    }
}
