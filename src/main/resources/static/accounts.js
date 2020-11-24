var accounts = [];

function findAccount(accountId) {
    return accounts[findAccountKey(accountId)];
}

function findAccountKey(accountId) {
    for (var key = 0; key < accounts.length; key++) {
        if (accounts[key].id == accountId) {
            return key;
        }
    }
}

var accountService = {
    findAll(fn) {
        axios
            .get('/api/v1/accounts')
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    findById(id, fn) {
        axios
            .get('/api/v1/accounts/' + id)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    create(account) {
        axios
            .post('/api/v1/accounts', account)
            .catch(error => console.log(error))
    },

    update(id, account) {
        axios
            .put('/api/v1/accounts/' + id, account)
            .catch(error => console.log(error))
    },

    deleteAccount(id) {
        axios
            .delete('/api/v1/accounts/' + id)
            .catch(error => console.log(error))
    }
}

var List = Vue.extend({
    template: '#account-list',
    data: function() {
        return { accounts: [], searchKey: '' };
    },
    computed: {
        filteredAccounts() {
            return this.accounts.filter((account) => {
                return account.name.indexOf(this.searchKey) > -1
                    || account.email.indexOf(this.searchKey) > -1
                    || account.age.toString().indexOf(this.searchKey) > -1
            })
        }
    },
    mounted() {
        accountService.findAll(r => { this.accounts = r.data; accounts = r.data })
    }
});

var Account = Vue.extend({
    template: '#account',
    data: function() {
        return { account: findAccount(this.$route.params.account_id) };
    }
});

var AccountEdit = Vue.extend({
    template: '#account-edit',
    data: function() {
        var account = findAccount(this.$route.params.account_id);
        if (account && (!account.addresses || account.addresses.length == 0)) {
            account.addresses = [{ address: '' }];
        }
        return { account: account };
    },
    methods: {
        updateAccount: function() {
            if (this.account && this.account.addresses) {
                for (var i = 0; i < this.account.addresses.length; i++) {
                    if (!this.account.addresses[i].address || this.account.addresses[i].address == '') {
                        this.account.addresses.splice(i, 1);
                    }
                }
            }
            accountService.update(this.account.id, this.account);
            accountService.findAll(r => { this.accounts = r.data; accounts = r.data })
            router.push('/');
        },
        add() {
            this.account.addresses.push({
                address: ''
            });
            console.log(this.account.addresses);
        },
        remove(index) {
            this.account.addresses.splice(index, 1)
        }
    }
});

var AccountDelete = Vue.extend({
    template: '#account-delete',
    data: function() {
        return { account: findAccount(this.$route.params.account_id) };
    },
    methods: {
        deleteAccount: function() {
            accountService.deleteAccount(this.account.id);
            accountService.findAll(r => { this.accounts = r.data; accounts = r.data })
            router.push('/');
        }
    }
});

var AddAccount = Vue.extend({
    template: '#add-account',
    data() {
        return {
            account: { name: '', email: '', age: 0, addresses: [{ address: '' }] }
        }
    },
    methods: {
        createAccount() {
            if (this.account && this.account.addresses) {
                for (var i = 0; i < this.account.addresses.length; i++) {
                    if (!this.account.addresses[i].address || this.account.addresses[i].address == '') {
                        this.account.addresses.splice(i, 1);
                    }
                }
            }
            accountService.create(this.account);
            accountService.findAll(r => { this.accounts = r.data; accounts = r.data })
            router.push('/');
        },
        add() {
            this.account.addresses.push({
                address: ''
            });
            console.log(this.account.addresses);
        },
        remove(index) {
            this.account.addresses.splice(index, 1)
        }
    }
});

var router = new VueRouter({
    routes: [
        { path: '/', component: List, name: 'account-list' },
        { path: '/account/:account_id', component: Account, name: 'account' },
        { path: '/add-account', component: AddAccount },
        { path: '/account/:account_id/edit', component: AccountEdit, name: 'account-edit' },
        { path: '/account/:account_id/delete', component: AccountDelete, name: 'account-delete' }
    ]
});

new Vue({
    router,
}).$mount('#app')
