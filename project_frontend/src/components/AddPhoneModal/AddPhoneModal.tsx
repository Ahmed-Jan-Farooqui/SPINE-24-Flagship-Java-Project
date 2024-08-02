import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from "@mui/material";

export default function AddPhoneModal({ open, handleAdd, handleClose }: any) {
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    const formData = new FormData(event.currentTarget);
    const formJson = Object.fromEntries((formData as any).entries());
    let number = formJson.number;
    let label = formJson.label;
    let id = -1;
    handleAdd(id, number, label, "add");
  };
  return (
    <Dialog
      open={open}
      onClose={handleClose}
      PaperProps={{
        component: "form",
        onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
          event.preventDefault();
          handleSubmit(event);
          handleClose();
        },
      }}
    >
      <DialogTitle>New Phone Number</DialogTitle>
      <DialogContent>
        <DialogContentText>Enter details below.</DialogContentText>
        <TextField
          autoFocus
          required
          margin="dense"
          id="name"
          name="number"
          label="Phone Number"
          type="text"
          fullWidth
          variant="standard"
        />
        <TextField
          autoFocus
          required
          margin="dense"
          id="name"
          name="label"
          label="Phone Number Label"
          type="text"
          fullWidth
          variant="standard"
        />
      </DialogContent>
      <DialogActions>
        <Button variant="outlined" onClick={handleClose}>
          Cancel
        </Button>
        <Button variant="contained" type="submit">
          Add
        </Button>
      </DialogActions>
    </Dialog>
  );
}
