<!--Add New Goal Modal -->
<div class="modal fade" id="addGoal" tabindex="-1" role="dialog" aria-labelledby="addGoal" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="addGoalLabel">New Goal</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <!-- 	private int id;
	private String username;
	private String name;
	private double amount;
	private Date created;
	private Date completed;
	private boolean status; -->
	
		<form action="addgoal" method="post">
			  <div class="form-group">
			    <label for="goalname">Goal Name</label>
			    <input type="text" class="form-control" id="goalname" name="name" required placeholder="Buy a new iPhone">
			  </div>
			  
			  <div class="form-group">
			    <label for="goalamount">Amount</label>
			    <input type="number" class="form-control" id="goalamount" name="amount" min="0" max="50000" required placeholder="15000">
			  </div>
			  <button class="btn btn-primary">add</button>
		</form>
      </div>
    </div>
  </div>
</div>


<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <form action="addmoney" method="post">
		  <div class="form-row align-items-center">
		    <div class="col-auto">
		      <label class="sr-only" for="amount">Amount</label>
		      <input type="number" class="form-control m-2" min="0" id="amount" name="amount" required placeholder="0000">
		    </div>
		    <div class="col-auto">
		      <button type="submit" class="btn btn-outline m-2">Submit</button>
		    </div>
		  </div>
		</form>
    </div>
  </div>
</div>