
    window.User = Backbone.Model.extend({
        urlRoot: "/web/user/all",

        defaults: {
            id:null,
            name: '',
            email:'',
            age: 0
        }
    });

    window.Users = Backbone.Collection.extend({
        model: User,
        url:"/web/user/all"
    });

    window.UsersView = Backbone.View.extend({
        initialize:function () {
//            this.model.bind("change", this.render, this);
            this.model.bind("reset", this.render, this);
//            this.model.bind("add", this.render, this);
            this.$el =  $("#user-table").find("tbody");

        },

        render: function(){
            var users = this.model.toJSON();
            var self = this;
            _(users).each(function(user){
               self.$el.append("<tr class=\"user_line\" ><td><input type=\"checkbox\" name=\"selected_user_id\" value=\""+user.id+"\"/></td><td>"+user.id+"</td><td>"
                    +user.name+"</td><td>"+user.email+"</td><td>"+user.age
                    +"</td><td><button class=\"btn\" onclick='remove_user( \""+user.id+"\", this)'>删除 </button></td></tr>");
            });

            return this;
        }
    });

    var AppRouter = Backbone.Router.extend({

        routes:{
            "":"list"
        },

        initialize:function () {
        },

        list:function () {
            this.users = new Users();
            this.usersView = new UsersView({model:this.users});
            this.users.fetch({reset: true});

        }

    });

    var app = new AppRouter();
    Backbone.history.start();
