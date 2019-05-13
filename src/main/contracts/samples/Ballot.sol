pragma solidity >=0.4.22 <0.7.0;
//https://solidity.readthedocs.io/en/develop/solidity-by-example.html

import "openzeppelin-solidity/contracts/ownership/Ownable.sol";
 
contract Ballot is Ownable{
    
    
    struct Proposal {
        bytes32 name;
        uint voteCount;
    }

    Proposal[] public proposals;

    address public chairpersion;

    
    struct Voter {
        uint weight;
        bool voted;
        address delegate;
        uint vote;
    }
    
    mapping(address => Voter) public voters;
    
    constructor(bytes32[] memory proposalNames) public {
        
        chairpersion = msg.sender;
        
        for(uint i = 0; i < proposalNames.length; i++){
            
            proposals.push(Proposal({ name: proposalNames[i], voteCount: 0}));
        }
        
    }
    
    function giveRightToVote(address voter) public onlyOwner{
        voters[voter].weight = 1;
        require(!voters[voter].voted, "This voter already voted.");
        require(voters[voter].weight == 0);
        voters[voter].weight = 1;
    }
    
    // @TODO How to controll the concurrency between this function and 'vote' function
    //       What if during 'delegate' function to addr1 and 'vote' function for addr1 proceed concurrently.
    //       Is locking necessary?
    function deletgate(address to) public {
        // validate self-delegation
        require(to != msg.sender, "Self-delegation is disallowed");

        // vaidate already voted
        Voter storage sender = voters[msg.sender];
        require(!sender.voted, "You already voted");
        
        while(voters[to].delegate != address(0)){
            to = voters[to].delegate;
            require(to != msg.sender, "Found loop in delegation.");
        }
        
        sender.voted = true;
        sender.delegate = to;
        Voter storage delegated = voters[to];
        if(delegated.voted){
            proposals[delegated.vote].voteCount += sender.weight;
        } else{
            //@TODO What if the 'vote' for 'to' address has completed right here.
            delegated.weight += delegated.weight;
        }
    }
    
}