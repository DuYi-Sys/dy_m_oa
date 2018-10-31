function open_new(){
	if (document.getElementsByTagName) {
		a = document.getElementsByTagName("a");
		for(i=0;i<a.length;i++)
		{
			if(a[i].getAttribute("rel") && a[i].getAttribute("rel") == "external")
			{
				a[i].onclick = function()
				{
					window.open(this.getAttribute("href"));
					return false;
				}
			}
		}
	}
	else return false;
}
