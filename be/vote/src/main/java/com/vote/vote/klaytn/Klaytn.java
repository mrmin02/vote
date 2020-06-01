package com.vote.vote.klaytn;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.klaytn.caver.Caver;
import com.klaytn.caver.crpyto.KlayCredentials;
import com.klaytn.caver.fee.FeePayerManager;
import com.klaytn.caver.methods.response.KlayTransactionReceipt;
import com.klaytn.caver.tx.ValueTransfer;
import com.klaytn.caver.tx.gas.DefaultGasProvider;
import com.klaytn.caver.tx.manager.TransactionManager;
import com.klaytn.caver.tx.model.SmartContractDeployTransaction;
import com.klaytn.caver.tx.model.SmartContractExecutionTransaction;
import com.klaytn.caver.utils.ChainId;
import com.klaytn.caver.utils.Convert;
import com.vote.vote.klaytn.smartContract.Test;
import com.vote.vote.klaytn.smartContract.Test6;
import com.vote.vote.klaytn.smartContract.VoteList;

// import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.json.simple.JSONObject;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Keys;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.utils.Numeric;

//import org.web3j.rlp.*;
public class Klaytn {

	public JSONObject createKey() throws Exception{ // 키 쌍으로 어카운트 생성
		KlayCredentials credentials =        
			    KlayCredentials.create(Keys.createEcKeyPair());
		// 계정 생성
		String privateKey = // 비밀키
			Numeric.toHexStringWithPrefix(
				credentials.getEcKeyPair().getPrivateKey());
		
		
		String address = credentials.getAddress(); // 어드레스값
			
			// 생성한 어카운트에는 
			//KlayCredentials credentials = KlayCredentials.create(<private key>); 이걸로 접근
			// System.out.println("privateKey: "+privateKey);
			// System.out.println("address: "+address);

		JSONObject result = new JSONObject();
		// result.putOpt("privateKey")
		result.put("privateKey",privateKey);
		result.put("address",address);
		return result;
	}
	
	// 수수료 대납 컨트랙트 배포  솔리디티 (int  getter setter ) 
	public JSONObject klaytnDeploy() throws Exception{ // 테스트 solidity 저장

		
		String sendUser = "0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0"; 

		String feeUser = "0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2"; 
		// 수수료 부담하는 사람  Private Key

		String data= "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060018190555061047d806100686000396000f3fe6080604052600436106100705760003560e01c80638da5cb5b1161004e5780638da5cb5b1461015c578063a87d942c146101b3578063b971691b146101de578063d0e30db01461023b57610070565b806312065fe01461007257806312514bba1461009d5780633bc5de30146100f0575b005b34801561007e57600080fd5b50610087610245565b6040518082815260200191505060405180910390f35b3480156100a957600080fd5b506100d6600480360360208110156100c057600080fd5b8101908080359060200190929190505050610264565b604051808215151515815260200191505060405180910390f35b3480156100fc57600080fd5b506101056102ca565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561014857808201518184015260208101905061012d565b505050509050019250505060405180910390f35b34801561016857600080fd5b50610171610322565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101bf57600080fd5b506101c8610347565b6040518082815260200191505060405180910390f35b3480156101ea57600080fd5b506102216004803603604081101561020157600080fd5b810190808035906020019092919080359060200190929190505050610351565b604051808215151515815260200191505060405180910390f35b6102436103f6565b005b60003073ffffffffffffffffffffffffffffffffffffffff1631905090565b60008161026f610245565b101561027a57600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051600060405180830381858888f193505050501580156102c0573d6000803e3d6000fd5b5060019050919050565b6060600280548060200260200160405190810160405280929190818152602001828054801561031857602002820191906000526020600020905b815481526020019060010190808311610304575b5050505050905090565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600154905090565b60008261035c610245565b101561036757600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc849081150290604051600060405180830381858888f193505050501580156103ad573d6000803e3d6000fd5b5060028290806001815401808255809150509060018203906000526020600020016000909192909190915055506001600081548092919060010191905055506001905092915050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461044f57600080fd5b56fea165627a7a723058204babebd74b60facf6d39a6bced9e9c5ad53a98d533fabb90acaa2415f6e280fc0029";


		
		// caver & 계정 로드
		Caver caver = Caver.build(Caver.BAOBAB_URL);
		KlayCredentials sender = KlayCredentials.create(sendUser);
		
		// SmartContracdeployTransaction 생성
		SmartContractDeployTransaction smartContractDeployTransaction = SmartContractDeployTransaction.create(
				sender.getAddress(), // 컨트렉트를 배포하고자 하는 계정의 주소
				BigInteger.ZERO, 
				Numeric.hexStringToByteArray(data), 
				new DefaultGasProvider().getGasLimit(), 
				BigInteger.ZERO);
		
		// sender의 계정으로 TransactionManager 인스턴스 생성
		TransactionManager txManager
			= new TransactionManager
				.Builder(caver, sender)
				.setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		String senderRawTransaction
			= txManager.sign(smartContractDeployTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create(feeUser);
		
		FeePayerManager feePayerManager
			= new FeePayerManager.Builder(caver,  feePayer)
				.setChainId(ChainId.BAOBAB_TESTNET).build();
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(senderRawTransaction);
		
		String txHash = transactionReceipt.getTransactionHash(); 
		String deployedContractAddress = transactionReceipt.getContractAddress();
		String errorMessage = transactionReceipt.getErrorMessage();
		
		System.out.println("txHash : "+txHash);
		System.out.println("deployedContractAddress : " + deployedContractAddress);
		System.out.println("errorMessage : " + errorMessage);

		JSONObject result = new JSONObject();
		result.put("address",deployedContractAddress);
		return result;
		
	}
	public JSONObject klaytnSend(String address, int select) throws Exception{ // 수수료 스마트 컨트렉트로 클레이 전송 // 솔리디티 파일로 데이터 전송
		Caver caver = Caver.build(Caver.BAOBAB_URL);

		KlayCredentials sender = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
		
		String str="1";
		//
//			byte[] byteArray=str.getBytes();
		
//			byte[] byteArray2 = "1212".getBytes();
//			String hexValue = javax.xml.bind.DatatypeConverter.printHexBinary(byteArray2);
		
		
		//Input byte array must be in range 0 < M <= 32 and length must match type 오류남.
		// 자바에선 16 비트 ? 로 바꿔서 길이가 63 ? 64 인데 , bytes32 에서는 길이를 32 까지 허용하니, 길이 오류가 남.
		
		
		Function function = new Function(
				Test6.FUNC_TRANSFERWITHDATA,  // FUNC_TRANSFER = "transfer"
		        Arrays.asList(
		        		
		        		new Uint256(BigInteger.ZERO),
		        		new Uint256(select)
		        		
		        		),  // inputParameters
		        Collections.emptyList()  // outputParameters          
		);
		String data = FunctionEncoder.encode(function);
		
		TransactionManager txManager = new TransactionManager.Builder(caver, sender)
                .setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		SmartContractExecutionTransaction smartContractExecutionTransaction
			= SmartContractExecutionTransaction.create(
				sender.getAddress(),//만드는 User 의 address
				address,
		        BigInteger.ZERO,
		        Numeric.hexStringToByteArray(data),       
//			        sendData, //전송할 데이터
		        new DefaultGasProvider().getGasLimit(Test6.FUNC_TRANSFER)
//			        BigInteger.valueOf(100_000)
		);
		System.out.println("smartContractExecutionTransaction:" +smartContractExecutionTransaction);
		String rawTransaction
		        = txManager.sign(smartContractExecutionTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");// 수수료 부담하는 사람의 private key
		FeePayerManager feePayerManager
		        = new FeePayerManager.Builder(caver, feePayer)
		        		.build();
//			                .setChainId(ChainId.BAOBAB_TESTNET).build();
		//Sync : FeePayer ExecuteTx
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(rawTransaction);
		String errorMessage = transactionReceipt.getErrorMessage();
		String txHash = transactionReceipt.getTransactionHash();
		
		System.out.println("txHash: "+txHash);
		System.out.println("errorMessage : "+ errorMessage);

		JSONObject json = new JSONObject();
		json.put("hash",txHash);
		return json;
	}
	public JSONObject load(String address) throws Exception{// 배포된 스마트 컨트렉트의 uint List 출력, count 출력 
			Caver caver = Caver.build(Caver.BAOBAB_URL);
			
			KlayCredentials credentials = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
			
//			0xcc1b2b7db95514cced8b7dcb169470eeda34ca76    -  대납
			Test6 eeToken = Test6.load(
//					"0x7b6c602b7e8dde60f16ec0684421d69868215f25",// 콘트렉트 adress 
//					"0x3970bec0c994b437d4bb2ccc277e965ce24f9ff6",
//					"0xb9be5c12aaf13a18e866883fccfa3327283d4891",
					// "0x3caef473574f6a9de0501d3073c5300e5a05ed1d",
					address,
					caver, 
					credentials, 
					ChainId.BAOBAB_TESTNET,
					new DefaultGasProvider());
				
			RemoteCall<List> remoteCall = eeToken.getData();

			System.out.println("Vote List : "+remoteCall.send());
			
			System.out.println("Count : "+eeToken.getCount().send());

			JSONObject json = new JSONObject();
			json.put("result",remoteCall.send());
			return json;
		}


	public JSONObject klaytnDeploy2() throws Exception{
		String sendUser = "0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0"; 

		String feeUser = "0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2"; 
		// 수수료 부담하는 사람  Private Key

		String data= "608060405260006002556003805534801561001957600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060018190555061077b806100716000396000f3fe6080604052600436106100c25760003560e01c80638da5cb5b1161007f578063b8f6860c11610059578063b8f6860c1461039a578063d0e30db014610401578063de8fa4311461040b578063e9ab77e514610436576100c2565b80638da5cb5b146102b1578063a87d942c14610308578063b36d8a9f14610333576100c2565b806312065fe0146100c457806312514bba146100ef578063170ab40514610142578063224348361461019557806350c42c78146101f25780637cd912c81461025e575b005b3480156100d057600080fd5b506100d9610468565b6040518082815260200191505060405180910390f35b3480156100fb57600080fd5b506101286004803603602081101561011257600080fd5b8101908080359060200190929190505050610487565b604051808215151515815260200191505060405180910390f35b34801561014e57600080fd5b5061017b6004803603602081101561016557600080fd5b81019080803590602001909291905050506104ed565b604051808215151515815260200191505060405180910390f35b3480156101a157600080fd5b506101d8600480360360408110156101b857600080fd5b810190808035906020019092919080359060200190929190505050610537565b604051808215151515815260200191505060405180910390f35b3480156101fe57600080fd5b50610207610551565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b8381101561024a57808201518184015260208101905061022f565b505050509050019250505060405180910390f35b34801561026a57600080fd5b506102976004803603602081101561028157600080fd5b81019080803590602001909291905050506105a9565b604051808215151515815260200191505060405180910390f35b3480156102bd57600080fd5b506102c66105f0565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561031457600080fd5b5061031d610615565b6040518082815260200191505060405180910390f35b34801561033f57600080fd5b506103806004803603606081101561035657600080fd5b8101908080359060200190929190803590602001909291908035906020019092919050505061061f565b604051808215151515815260200191505060405180910390f35b3480156103a657600080fd5b506103e7600480360360608110156103bd57600080fd5b81019080803590602001909291908035906020019092919080359060200190929190505050610641565b604051808215151515815260200191505060405180910390f35b6104096106d6565b005b34801561041757600080fd5b50610420610731565b6040518082815260200191505060405180910390f35b34801561044257600080fd5b5061044b61073e565b604051808381526020018281526020019250505060405180910390f35b60003073ffffffffffffffffffffffffffffffffffffffff1631905090565b600081610492610468565b101561049d57600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051600060405180830381858888f193505050501580156104e3573d6000803e3d6000fd5b5060019050919050565b60005b816004805490501461052e576004600090806001815401808255809150509060018203906000526020600020016000909192909190915055506104f0565b60019050919050565b600082600281905550816003819055506001905092915050565b6060600480548060200260200160405190810160405280929190818152602001828054801561059f57602002820191906000526020600020905b81548152602001906001019080831161058b575b5050505050905090565b6000600460018303815481106105bb57fe5b906000526020600020016000815480929190600101919050555060016000815480929190600101919050555060019050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600154905090565b600061062b8484610537565b50610635826104ed565b50600190509392505050565b6000816002541115801561065757506003548211155b156106ca5783610665610468565b101561067057600080fd5b3373ffffffffffffffffffffffffffffffffffffffff166108fc859081150290604051600060405180830381858888f193505050501580156106b6573d6000803e3d6000fd5b506106c0836105a9565b50600190506106cf565b600090505b9392505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461072f57600080fd5b565b6000600480549050905090565b60008060025460035491509150909156fea165627a7a723058205b460daadc7235e63a3a619b4b0fc41acd4508a9915e101db7992e792842b81f0029";


		
		// caver & 계정 로드
		Caver caver = Caver.build(Caver.BAOBAB_URL);
		KlayCredentials sender = KlayCredentials.create(sendUser);
		
		// SmartContracdeployTransaction 생성
		SmartContractDeployTransaction smartContractDeployTransaction = SmartContractDeployTransaction.create(
				sender.getAddress(), // 컨트렉트를 배포하고자 하는 계정의 주소
				BigInteger.ZERO, 
				Numeric.hexStringToByteArray(data), 
				new DefaultGasProvider().getGasLimit(), 
				BigInteger.ZERO);
		
		// sender의 계정으로 TransactionManager 인스턴스 생성
		TransactionManager txManager
			= new TransactionManager
				.Builder(caver, sender)
				.setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		String senderRawTransaction
			= txManager.sign(smartContractDeployTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create(feeUser);
		
		FeePayerManager feePayerManager
			= new FeePayerManager.Builder(caver,  feePayer)
				.setChainId(ChainId.BAOBAB_TESTNET).build();
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(senderRawTransaction);
		
		String txHash = transactionReceipt.getTransactionHash(); 
		String deployedContractAddress = transactionReceipt.getContractAddress();
		String errorMessage = transactionReceipt.getErrorMessage();
		
		System.out.println("txHash : "+txHash);
		System.out.println("deployedContractAddress : " + deployedContractAddress);
		System.out.println("errorMessage : " + errorMessage);

		JSONObject result = new JSONObject();
		result.put("address",deployedContractAddress);
		return result;
	}

	public JSONObject klaytnSend2(String address, int select, long time)throws Exception{ 
		Caver caver = Caver.build(Caver.BAOBAB_URL);

		KlayCredentials sender = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
		//
//			byte[] byteArray=str.getBytes();
		
//			byte[] byteArray2 = "1212".getBytes();
//			String hexValue = javax.xml.bind.DatatypeConverter.printHexBinary(byteArray2);
		
		
		//Input byte array must be in range 0 < M <= 32 and length must match type 오류남.
		// 자바에선 16 비트 ? 로 바꿔서 길이가 63 ? 64 인데 , bytes32 에서는 길이를 32 까지 허용하니, 길이 오류가 남.
		
		
		Function function = new Function(
			VoteList.FUNC_TRANSFERWITHDATA,  // FUNC_TRANSFER = "transfer"
		        Arrays.asList(
		        		
		        		new Uint256(BigInteger.ZERO),
						new Uint256(select),
						new Uint256(time)
		        		
		        		),  // inputParameters
		        Collections.emptyList()  // outputParameters          
		);
		String data = FunctionEncoder.encode(function);
		
		TransactionManager txManager = new TransactionManager.Builder(caver, sender)
                .setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		SmartContractExecutionTransaction smartContractExecutionTransaction
			= SmartContractExecutionTransaction.create(
				sender.getAddress(),//만드는 User 의 address
				address,
		        BigInteger.ZERO,
		        Numeric.hexStringToByteArray(data),       
//			        sendData, //전송할 데이터
		        new DefaultGasProvider().getGasLimit(Test6.FUNC_TRANSFER)
//			        BigInteger.valueOf(100_000)
		);
		System.out.println("smartContractExecutionTransaction:" +smartContractExecutionTransaction);
		String rawTransaction
		        = txManager.sign(smartContractExecutionTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");// 수수료 부담하는 사람의 private key
		FeePayerManager feePayerManager
		        = new FeePayerManager.Builder(caver, feePayer)
		        		.build();
//			                .setChainId(ChainId.BAOBAB_TESTNET).build();
		//Sync : FeePayer ExecuteTx
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(rawTransaction);
		String errorMessage = transactionReceipt.getErrorMessage();
		String txHash = transactionReceipt.getTransactionHash();
		
		System.out.println("txHash: "+txHash);
		System.out.println("errorMessage : "+ errorMessage);

		JSONObject json = new JSONObject();
		json.put("hash",txHash);
		return json;
	}

	public JSONObject klaytnSetOptions(String address, long startTime, long endTime, int limit) throws Exception{
		Caver caver = Caver.build(Caver.BAOBAB_URL);

		KlayCredentials sender = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
		//
//			byte[] byteArray=str.getBytes();
		
//			byte[] byteArray2 = "1212".getBytes();
//			String hexValue = javax.xml.bind.DatatypeConverter.printHexBinary(byteArray2);
		
		
		//Input byte array must be in range 0 < M <= 32 and length must match type 오류남.
		// 자바에선 16 비트 ? 로 바꿔서 길이가 63 ? 64 인데 , bytes32 에서는 길이를 32 까지 허용하니, 길이 오류가 남.
		
		
		Function function = new Function(
			VoteList.FUNC_SETOPTIONS,  // FUNC_TRANSFER = "transfer"
		        Arrays.asList(
		        		
		        		new Uint256(startTime),
						new Uint256(endTime),
						new Uint256(limit)
		        		
		        		),  // inputParameters
		        Collections.emptyList()  // outputParameters          
		);
		String data = FunctionEncoder.encode(function);
		
		TransactionManager txManager = new TransactionManager.Builder(caver, sender)
                .setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		SmartContractExecutionTransaction smartContractExecutionTransaction
			= SmartContractExecutionTransaction.create(
				sender.getAddress(),//만드는 User 의 address
				address,
		        BigInteger.ZERO,
		        Numeric.hexStringToByteArray(data),       
//			        sendData, //전송할 데이터
		        new DefaultGasProvider().getGasLimit(Test6.FUNC_TRANSFER)
//			        BigInteger.valueOf(100_000)
		);
		System.out.println("smartContractExecutionTransaction:" +smartContractExecutionTransaction);
		String rawTransaction
		        = txManager.sign(smartContractExecutionTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");// 수수료 부담하는 사람의 private key
		FeePayerManager feePayerManager
		        = new FeePayerManager.Builder(caver, feePayer)
		        		.build();
//			                .setChainId(ChainId.BAOBAB_TESTNET).build();
		//Sync : FeePayer ExecuteTx
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(rawTransaction);
		String errorMessage = transactionReceipt.getErrorMessage();
		String txHash = transactionReceipt.getTransactionHash();
		
		System.out.println("txHash: "+txHash);
		System.out.println("errorMessage : "+ errorMessage);

		JSONObject json = new JSONObject();
		json.put("hash",txHash);
		return json;
	}

	public JSONObject klaytnSetTime(String address, long startTime, long endTime) throws Exception{
		Caver caver = Caver.build(Caver.BAOBAB_URL);

		KlayCredentials sender = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
		//
//			byte[] byteArray=str.getBytes();
		
//			byte[] byteArray2 = "1212".getBytes();
//			String hexValue = javax.xml.bind.DatatypeConverter.printHexBinary(byteArray2);
		
		
		//Input byte array must be in range 0 < M <= 32 and length must match type 오류남.
		// 자바에선 16 비트 ? 로 바꿔서 길이가 63 ? 64 인데 , bytes32 에서는 길이를 32 까지 허용하니, 길이 오류가 남.
		
		
		Function function = new Function(
			VoteList.FUNC_SETTIMES,  // FUNC_TRANSFER = "transfer"
		        Arrays.asList(
		        		
		        		new Uint256(startTime),
						new Uint256(endTime)
		        		
		        		),  // inputParameters
		        Collections.emptyList()  // outputParameters          
		);
		String data = FunctionEncoder.encode(function);
		
		TransactionManager txManager = new TransactionManager.Builder(caver, sender)
                .setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		SmartContractExecutionTransaction smartContractExecutionTransaction
			= SmartContractExecutionTransaction.create(
				sender.getAddress(),//만드는 User 의 address
				address,
		        BigInteger.ZERO,
		        Numeric.hexStringToByteArray(data),       
//			        sendData, //전송할 데이터
		        new DefaultGasProvider().getGasLimit(Test6.FUNC_TRANSFER)
//			        BigInteger.valueOf(100_000)
		);
		System.out.println("smartContractExecutionTransaction:" +smartContractExecutionTransaction);
		String rawTransaction
		        = txManager.sign(smartContractExecutionTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");// 수수료 부담하는 사람의 private key
		FeePayerManager feePayerManager
		        = new FeePayerManager.Builder(caver, feePayer)
		        		.build();
//			                .setChainId(ChainId.BAOBAB_TESTNET).build();
		//Sync : FeePayer ExecuteTx
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(rawTransaction);
		String errorMessage = transactionReceipt.getErrorMessage();
		String txHash = transactionReceipt.getTransactionHash();
		
		System.out.println("txHash: "+txHash);
		System.out.println("errorMessage : "+ errorMessage);

		JSONObject json = new JSONObject();
		json.put("hash",txHash);
		return json;
	}

	public JSONObject klaytnSetLimit(String address, int limit) throws Exception{
		Caver caver = Caver.build(Caver.BAOBAB_URL);

		KlayCredentials sender = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
		//
//			byte[] byteArray=str.getBytes();
		
//			byte[] byteArray2 = "1212".getBytes();
//			String hexValue = javax.xml.bind.DatatypeConverter.printHexBinary(byteArray2);
		
		
		//Input byte array must be in range 0 < M <= 32 and length must match type 오류남.
		// 자바에선 16 비트 ? 로 바꿔서 길이가 63 ? 64 인데 , bytes32 에서는 길이를 32 까지 허용하니, 길이 오류가 남.
		
		
		Function function = new Function(
			VoteList.FUNC_SETSIZE,  // FUNC_TRANSFER = "transfer"
		        Arrays.asList(
		        		
		        		new Uint256(limit)
						
		        		
		        		),  // inputParameters
		        Collections.emptyList()  // outputParameters          
		);
		String data = FunctionEncoder.encode(function);
		
		TransactionManager txManager = new TransactionManager.Builder(caver, sender)
                .setChaindId(ChainId.BAOBAB_TESTNET).build();
		
		SmartContractExecutionTransaction smartContractExecutionTransaction
			= SmartContractExecutionTransaction.create(
				sender.getAddress(),//만드는 User 의 address
				address,
		        BigInteger.ZERO,
		        Numeric.hexStringToByteArray(data),       
//			        sendData, //전송할 데이터
		        new DefaultGasProvider().getGasLimit(Test6.FUNC_TRANSFER)
//			        BigInteger.valueOf(100_000)
		);
		System.out.println("smartContractExecutionTransaction:" +smartContractExecutionTransaction);
		String rawTransaction
		        = txManager.sign(smartContractExecutionTransaction, true).getValueAsString();
		
		KlayCredentials feePayer = KlayCredentials.create("0xe6dde3422998e00bbd13002e1396388d9b0e4f6ab3de630ce8167ff2c99057f2");// 수수료 부담하는 사람의 private key
		FeePayerManager feePayerManager
		        = new FeePayerManager.Builder(caver, feePayer)
		        		.build();
//			                .setChainId(ChainId.BAOBAB_TESTNET).build();
		//Sync : FeePayer ExecuteTx
		KlayTransactionReceipt.TransactionReceipt transactionReceipt 
			= feePayerManager.executeTransaction(rawTransaction);
		String errorMessage = transactionReceipt.getErrorMessage();
		String txHash = transactionReceipt.getTransactionHash();
		
		System.out.println("txHash: "+txHash);
		System.out.println("errorMessage : "+ errorMessage);

		JSONObject json = new JSONObject();
		json.put("hash",txHash);
		return json;
	}

	public JSONObject load2(String address) throws Exception{// 배포된 스마트 컨트렉트의 uint List 출력, count 출력 
		Caver caver = Caver.build(Caver.BAOBAB_URL);
		
		KlayCredentials credentials = KlayCredentials.create("0x26efd4a0c62543128ac05b4f3b29668d43c18a43531b5aecb85e387dd1dc0bd0");
		
//			0xcc1b2b7db95514cced8b7dcb169470eeda34ca76    -  대납
		VoteList eeToken = VoteList.load(
//					"0x7b6c602b7e8dde60f16ec0684421d69868215f25",// 콘트렉트 adress 
//					"0x3970bec0c994b437d4bb2ccc277e965ce24f9ff6",
//					"0xb9be5c12aaf13a18e866883fccfa3327283d4891",
				// "0x3caef473574f6a9de0501d3073c5300e5a05ed1d",
				address,
				caver, 
				credentials, 
				ChainId.BAOBAB_TESTNET,
				new DefaultGasProvider());
			
		RemoteCall<List> remoteCall = eeToken.getListVote();

		System.out.println("Vote List : "+remoteCall.send());
		
		System.out.println("Count : "+eeToken.getCount().send());

		JSONObject json = new JSONObject();
		json.put("result",remoteCall.send());
		json.put("count",eeToken.getCount().send());
		return json;
	}
}
