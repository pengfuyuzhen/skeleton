package controllers;

import api.CreateTagRequest;
import api.ReceiptResponse;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/{tag}")
    public void toggleTag(@PathParam("tag") String tagLabel, @NotNull CreateTagRequest tag) {
        if (tags.isReceiptTagged(tagLabel, tag.receiptId)) {
            tags.remove(tagLabel, tag.receiptId);
        } else {
            tags.insert(tagLabel, tag.receiptId);
        }
    }

    @GET
    @Path("/{tag}")
    public List<ReceiptResponse> getReceiptsWithTag(@PathParam("tag") String tagLabel) {
        List<ReceiptsRecord> receiptRecords = tags.receiptsRecordsWithTag(tagLabel);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(Collectors.toList());
    }
}
