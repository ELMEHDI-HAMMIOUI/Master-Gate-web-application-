function datatables() {
  return {
    headings: [
      {
        key: "userId",
        value: "User ID"
      },
      {
        key: "firstName",
        value: "Firstname"
      },
      {
        key: "lastName",
        value: "Lastname"
      },
      {
        key: "emailAddress",
        value: "Email"
      },
      {
        key: "gender",
        value: "Gender"
      },
      {
        key: "phoneNumber",
        value: "Phone"
      }
    ],
    users: [
      {
        selected: false,
        userId: 1,
        firstName: "Cort",
        lastName: "Tosh",
        emailAddress: "ctosh0@github.com",
        gender: "Male",
        phoneNumber: "327-626-5542"
      },
      {
        selected: false,
        userId: 2,
        firstName: "Brianne",
        lastName: "Dzeniskevich",
        emailAddress: "bdzeniskevich1@hostgator.com",
        gender: "Female",
        phoneNumber: "144-190-8956"
      },
      {
        selected: false,
        userId: 3,
        firstName: "Isadore",
        lastName: "Botler",
        emailAddress: "ibotler2@gmpg.org",
        gender: "Male",
        phoneNumber: "350-937-0792"
      },
      {
        selected: false,
        userId: 4,
        firstName: "Janaya",
        lastName: "Klosges",
        emailAddress: "jklosges3@amazon.de",
        gender: "Female",
        phoneNumber: "502-438-7799"
      },
      {
        selected: false,
        userId: 5,
        firstName: "Freddi",
        lastName: "Di Claudio",
        emailAddress: "fdiclaudio4@phoca.cz",
        gender: "Female",
        phoneNumber: "265-448-9627"
      },
      {
        selected: false,
        userId: 6,
        firstName: "Oliy",
        lastName: "Mairs",
        emailAddress: "omairs5@fda.gov",
        gender: "Female",
        phoneNumber: "221-516-2295"
      },
      {
        selected: false,
        userId: 7,
        firstName: "Tabb",
        lastName: "Wiseman",
        emailAddress: "twiseman6@friendfeed.com",
        gender: "Male",
        phoneNumber: "171-817-5020"
      },
      {
        selected: false,
        userId: 8,
        firstName: "Joela",
        lastName: "Betteriss",
        emailAddress: "jbetteriss7@msu.edu",
        gender: "Female",
        phoneNumber: "481-100-9345"
      },
      {
        selected: false,
        userId: 9,
        firstName: "Alistair",
        lastName: "Vasyagin",
        emailAddress: "avasyagin8@gnu.org",
        gender: "Male",
        phoneNumber: "520-669-8364"
      },
      {
        selected: false,
        userId: 10,
        firstName: "Nealon",
        lastName: "Ratray",
        emailAddress: "nratray9@typepad.com",
        gender: "Male",
        phoneNumber: "993-654-9793"
      },
      {
        selected: false,
        userId: 11,
        firstName: "Annissa",
        lastName: "Kissick",
        emailAddress: "akissicka@deliciousdays.com",
        gender: "Female",
        phoneNumber: "283-425-2705"
      },
      {
        selected: false,
        userId: 12,
        firstName: "Nissie",
        lastName: "Sidnell",
        emailAddress: "nsidnellb@freewebs.com",
        gender: "Female",
        phoneNumber: "754-391-3116"
      },
      {
        selected: false,
        userId: 13,
        firstName: "Madalena",
        lastName: "Fouch",
        emailAddress: "mfouchc@mozilla.org",
        gender: "Female",
        phoneNumber: "584-300-9004"
      },
      {
        selected: false,
        userId: 14,
        firstName: "Rozina",
        lastName: "Atkins",
        emailAddress: "ratkinsd@japanpost.jp",
        gender: "Female",
        phoneNumber: "792-856-0845"
      },
      {
        selected: false,
        userId: 15,
        firstName: "Lorelle",
        lastName: "Sandcroft",
        emailAddress: "lsandcrofte@google.nl",
        gender: "Female",
        phoneNumber: "882-911-7241"
      }
    ],

    open: false,
    
    search: '',
    
    columns: [],

    get selectedUsers() {
      return this.users.filter((user) => user.selected);
    },

    init() {
      this.columns = this.headings.map((h) => {
        return h.key;
      });
    },

    toggleColumn(key) {
      this.columns.includes(key)
        ? (this.columns = this.columns.filter((i) => i !== key))
        : this.columns.push(key);
    },

    selectAllCheckbox() {
      let filteredUsers = this.filtered(this.users);
      if (filteredUsers.length === this.selectedUsers.length) {
        return filteredUsers.map((user) => (user.selected = false));
      }
      filteredUsers.map((user) => (user.selected = true));
    },

    filtered(...items) {
      // Search filter Function for any Array of Objects !
      
      // You can pass only the Array of Objects, 
      // it will search all props of every Object except "ID"
      // Example : filtered(users)
      
      // OR you can pass additional props, it will only search passed props
      // Example : filtered(users, 'firstName', 'lastName','emailAddress', 'phoneNumber')

      values = items.shift(); // get the list of objects
      props = items.length ? items : null; // get list of props

      return values.filter((i) => {
        y = Object.assign({}, i);
        delete y['userId']; // Specifie the id prop to remove from object
        if (props) {
          okeys = Object.keys(y).filter((b) => !props.includes(b));
          okeys.map((d) => delete y[d]);
        }
        itemToSearch = Object.values(y).join(); // Object to array, then join to String
        return itemToSearch.toLowerCase().includes(this.search.toLowerCase()); // Return filtred Object
      });
    }
  };
}
