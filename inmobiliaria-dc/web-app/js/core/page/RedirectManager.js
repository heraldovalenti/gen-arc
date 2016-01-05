function RedirectManager() {
	this.defaultManager : new ListPageManager(),
    this.pageManagers : {},
	this.currentManager: undefined
}
RedirectManager.prototype.makeRedirect = function() {
	this.currentManager.render();
}
RedirectManager.prototype.