<div class="modal" id="modal_Login">

	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h2 class="modal-title text-center fc-orange">Log in</h2>
				</div>
				<div class="modal-body">
			       <form id="fo_login_widget" action="{getUrl('','act','procMemberLogin')}" method="post" ruleset="@login" role="form" class="clearfix">
					<fieldset id="acField">
						<input type="hidden" name="act" value="procMemberLogin" />
						<input type="hidden" name="success_return_url" value="{htmlspecialchars(getRequestUriByServerEnviroment(), ENT_COMPAT | ENT_HTML401, 'UTF-8', false)}" />
						<input type="hidden" name="xe_validator_id" value="widgets/login_info/skins/default/login_form/1" />
						<div cond="$XE_VALIDATOR_MESSAGE && $XE_VALIDATOR_ID == 'widgets/login_info/skins/default/login_form/1'" class="message {$XE_VALIDATOR_MESSAGE_TYPE}">
							<p>
								{$XE_VALIDATOR_MESSAGE}
							</p>
						</div>
						<div class="login-left">
							<div class="form-group">
								<button type="submit" class="butn butn-primary">
									<span>Facebook Login</span>
								</button>
							</div>
							<div class="form-group">
								<button type="submit" class="butn butn-info">
									<span>Twitter Login</span>
								</button>
							</div>
						</div>
						<div class="login-bar hidden-xs"></div>
						<div class="login-right">
							<div class="form-group">
								<label for="user_id" cond="$member_config->identifier != 'email_address'">{$lang->user_id}</label>
								<input name="user_id" id="user_id" type="text" required cond="$member_config->identifier != 'email_address'" class="form-control" placeholder="Your ID" />
							</div>
							<div class="form-group">
								<label for="user_id" cond="$member_config->identifier == 'email_address'">{$lang->email_address}</label>
								<input name="user_id" id="user_id" type="email" required cond="$member_config->identifier == 'email_address'" class="form-control" placeholder="Your Email" />

							</div>
							<div class="form-group">
								<label for="user_pw">{$lang->password}</label>
								<input name="password" id="user_pw" class="form-control" type="password" required placeholder="Password" />
							</div>
							<ul class="help list-unstyled list-inline">
								<li>
									<a href="{getUrl('act','dispMemberSignUpForm')}">{$lang->cmd_signup}</a>
								</li>
								<li>
									<a href="{getUrl('act','dispMemberFindAccount')}">{$lang->cmd_find_member_account}</a>
								</li>
							</ul>
							<button type="submit" class="butn butn-warning butn-block">
								Sign in
							</button>
						</div>
					</fieldset>
				</form>
				</div>
			</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->





<!--@if($logged_info)-->
<li><a href="{getUrl('act','dispMemberInfo','member_srl','')}" title="{$lang->last_login}: {zDate($logged_info->last_login, "Y-m-d")}">{$logged_info->nick_name}</a></li>

<li><a href="{getUrl('act', 'dispMemberLogout')}" onclick="if (!confirm('{$lang->confirm_logout}')) return false">LOGUT</a></li>

<li><a cond="$grant->manager" href="{getUrl('', 'module', 'admin')}" target="_blank">Admin</a></li>

<!--@else-->

<!--<li><a href="{getUrl('act', 'dispMemberLoginForm')}">Login</span></a></li>-->

<li><a href="" data-target="#modal_Login" type="button"  data-toggle="modal"><span>LOGIN | SIGN UP</span></a></li>

<!--@end-->							
