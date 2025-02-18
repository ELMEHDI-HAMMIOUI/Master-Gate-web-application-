        // show the settings options
        document.getElementById("settings-btn").addEventListener("click",(e)=>{
            e.preventDefault();
            document.querySelector(".settings-chevron").classList.toggle("chevron-down");
            document.getElementById("control-btns").classList.toggle("hidden");
        })

        
        //accordion
        document.querySelector(".p-main-section-title").addEventListener("click", (e)=>{
        	e.target.parentNode.querySelectorAll(".p-info-wraper").forEach((wrap)=>{
        		wrap.classList.toggle("hidden");
        	})
        })
        